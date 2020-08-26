package com.hifive.api.internal.jushita.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 此类实现一个FIFO的队列，用于容纳单topic多连接场景中推送下来的数据。不做范型了，因为肯定是那个数据.
 * <p>
 *     引入了检查和报告两种动作，同时每一个元素都有状态，并且都自知自己在队列中的位置。
 * </p>
 *
 * Created by IntelliJ IDEA.
 * User: guichen - anson
 * Date: 12-8-20
 */
public class MessageCircleQueue {

	private final Lock lock = new ReentrantLock();
	private Condition putCondition = lock.newCondition();
	private Condition takeCondition = lock.newCondition();

	private Message[] messageContent;

	private int producerP = 0;
	private int consumerP = 0;
	private int checkP = 0;
	private int reportP = 0;

	private int size;
	private int unreportedCount = 0;
	private long messageTimeout = 10000l;

	private int checkedCount = 0;

	public MessageCircleQueue(int size) {
		//no inspection unchecked
		this.messageContent = new Message[size];
		this.size = size;
	}

	public MessageCircleQueue(int size, int timeoutSecond) {
		this(size);
		this.messageTimeout = timeoutSecond * 1000l;
	}

	/**
	 * 此方法压入元素并循环递增生产指针，当遇到报告指针时wait直到报告指针被往前移动。
	 *
	 * @param message 需要put的消息
	 * @return put进去的元素在队列中的位置
	 * @throws InterruptedException
	 */
	public int put(Message message) throws InterruptedException {
		lock.lock();
		try {
			// 当未report的消息数量满了时阻塞 因为可能的虚假唤醒 所以要在while里wait
			while (unreportedCount == size) {
				putCondition.await();
			}
			// 放入消息的时候带上自己的位置
			message.setIndex(producerP);
			messageContent[producerP] = message;
			int tempP = producerP;
			if (++producerP == size) producerP = 0;
			++unreportedCount;
			// 唤醒一个take阻塞的线程
			takeCondition.signal();
			return tempP;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 此方法弹出元素并递增消费指针，当遇到生产指针时wait直到生产指针被往前移动。
	 *
	 * @return 消息
	 * @throws InterruptedException
	 */
	public Message take() throws InterruptedException {
		lock.lock();
		try {
			while (consumerP == producerP) {
				takeCondition.await();
			}
			Message message = messageContent[consumerP];
			message.taken();
			if (++consumerP == size) consumerP = 0;
			return message;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 此方法移动确认指针尽可能长的距离，直到遇到未确认也未超时的消息
	 * <p>
	 *     此方法不是线程安全的，应当仅在单个线程中调用它或者在外部同步
	 * </p>
	 *
	 * @return 这次确认的消息数量
	 */
	public int check() {
		// 为了避免确认指针和消费指针重合的情况（就是消费指针领跑一圈），先行
		do {
			Message message = messageContent[checkP];
			// 如果t的状态是UNKNOWN并且没到达超时时间 就跳出
			if (message == null || (message.getState() == Message.State.UNKNOWN
					&& (System.currentTimeMillis() - message.getTakeTime()) < messageTimeout)) {
				break;
			}
			++checkedCount;
			if (++checkP == size) checkP = 0;
		} while (checkP != consumerP);
		return checkedCount;
	}

	/**
	 * 此方法统计上一次报告位置到现在确认位置之间的消息并生成报告
	 * <p>
	 *     此方法本身是线程安全的，但是会影响check方法的状态，所以应该只在单线程中顺序的调用check方法和此方法，或者在外部保持同步。
	 *     一般来说应该成对的调用check和report方法
	 * </p>
	 *
	 * @return 报告
	 */
	public Report report() {
		lock.lock();
		try {
			if (checkedCount == 0) return null;
			Report report = new Report();
			Map<Long, String> successMap = new HashMap<Long, String>();
			// 当checkedCount等于size的时候 就是整个队列都被塞满了 确认指针领跑报告指针一圈 仍然要进行report动作
			while (reportP != checkP || checkedCount == size) {
				Message message = messageContent[reportP];
				messageContent[reportP] = null;
				switch (message.getState()) {
				case SUCCESS:
					// 永远覆盖
					successMap.put(message.getNextOffsetDO().id, message.getNextOffset());
					break;
				case FAILED:
				case UNKNOWN:
					// 失败和未知（这里可以遇到的UNKNOWN都是超时了的）都算失败
					report.getfOffset().add(message.getOffset());
				}
				if (++reportP == size) reportP = 0;
				--unreportedCount;
				--checkedCount;
			}
			report.getsOffset().addAll(successMap.values());
			// 唤醒所有wait在put的线程 因为一次report会空出多个空间
			putCondition.signalAll();
			return report;
		} finally {
			lock.unlock();
		}
	}
}
