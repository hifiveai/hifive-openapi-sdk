package com.hifive.link.channel.netty;


import com.hifive.link.Logger;
import com.hifive.link.LoggerFactory;
import com.hifive.link.Text;
import com.hifive.link.channel.ChannelContext;
import com.hifive.link.channel.ChannelSender;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.ChannelGroup;
import com.hifive.link.channel.ChannelHandler;
import java.io.IOException;

public abstract class NettyServerUpstreamHandler extends SimpleChannelUpstreamHandler {
	protected Logger logger;
	protected Logger ioErrorLogger;
	protected ChannelHandler channelHandler;
	protected ChannelGroup allChannels;
	protected ChannelSender sender;
	protected String closedReason;

	public NettyServerUpstreamHandler(LoggerFactory loggerFactory,
									  ChannelHandler channelHandler,
									  ChannelGroup channelGroup) {
		this.logger = loggerFactory.create(this);
		this.ioErrorLogger = loggerFactory.create(this.getClass().getSimpleName() + ".IOError");
		this.channelHandler = channelHandler;
		this.allChannels = channelGroup;
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) {
		this.allChannels.add(e.getChannel());
		this.sender = this.createSender(e.getChannel());
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		if (this.closedReason == null)
			this.logger.warn(Text.TCP_CHANNEL_CLOSED);
		if (this.channelHandler != null)
			this.channelHandler.onClosed(this.closedReason);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		Throwable t = e.getCause();

		if (this.channelHandler != null)
			this.channelHandler.onError(this.createContext(t));

		e.getChannel().close();

		if (t instanceof IOException)
			this.ioErrorLogger.error(Text.ERROR_AT_SERVER, t);
		else
			this.logger.error(Text.ERROR_AT_SERVER, t);
	}
	
	protected abstract ChannelSender createSender(Channel channel);

	protected ChannelContext createContext(Object message) {
		ChannelContext ctx = new ChannelContext();
		ctx.setSender(this.sender);
		ctx.setMessage(message);
		return ctx;
	}

	protected ChannelContext createContext(Throwable error) {
		ChannelContext ctx = new ChannelContext();
		ctx.setSender(this.sender);
		ctx.setError(error);
		return ctx;
	}
}