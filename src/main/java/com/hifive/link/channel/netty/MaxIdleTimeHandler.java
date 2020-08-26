package com.hifive.link.channel.netty;

import com.hifive.link.Logger;
import com.hifive.link.LoggerFactory;
import com.hifive.link.Text;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

// IdleStateHandler.
// http://docs.jboss.org/netty/3.2/api/org/jboss/netty/handler/timeout/IdleStateHandler.html
public class MaxIdleTimeHandler extends IdleStateAwareChannelHandler {
	private Logger logger;
	private int maxIdleTimeSeconds;

	public MaxIdleTimeHandler(LoggerFactory loggerFactory, int maxIdleTimeSeconds) {
		this.logger = loggerFactory.create(this);
		this.maxIdleTimeSeconds = maxIdleTimeSeconds;
	}

	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws InterruptedException {
		if (e.getState() == IdleState.ALL_IDLE) {
			this.closeChannel(ctx, 1011, Text.REACH_MAX_IDLE);
			this.logger.info(Text.REACH_MAX_IDLE_AND_CLOSE, this.maxIdleTimeSeconds);
		}
	}

	private void closeChannel(ChannelHandlerContext ctx, int statusCode, String reason) throws InterruptedException {
		ctx.getChannel().write(new CloseWebSocketFrame(statusCode, reason));
		ctx.getChannel().close();
	}
}
