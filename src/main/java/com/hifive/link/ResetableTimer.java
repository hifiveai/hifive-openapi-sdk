package com.hifive.link;

// https://gist.github.com/wsky/5538632
// easy timer task, support delay and reset
public class ResetableTimer {

	private volatile boolean running;
	private Thread boss;
	private Runnable task;
	private long period;
	private long lastTime;

	public ResetableTimer(long periodMillisecond) {
		this(periodMillisecond, null);
	}

	public ResetableTimer(long periodMillisecond, Runnable task) {
		this.period = periodMillisecond;
		this.delay(0 - this.period);
		this.setTask(task);
	}

	public void setTask(Runnable task) {
		this.task = task;
	}

	public void start() {
		if (this.boss != null)
			return;
		this.running = true;
		this.boss = new Thread(new Runnable() {
			public void run() {
				while (running) {
					long split = System.currentTimeMillis() - lastTime;
					if (split >= period && task != null) {
						try {
							task.run();
						} catch (Exception e) {
							e.printStackTrace();
						}
						delay();
					}
					try {
						Thread.sleep(split >= period ? period : period - split);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		}, "tmc-heartbeat");
		this.boss.setDaemon(true);
		this.boss.start();
	}

	public void stop() throws InterruptedException {
		this.running = false;
		this.boss.interrupt();
		this.boss.join();
		this.boss = null;
	}

	public void delay() {
		this.delay(0);
	}

	public void delay(long delayMillisecond) {
		this.lastTime = System.currentTimeMillis() + delayMillisecond;
	}
}
