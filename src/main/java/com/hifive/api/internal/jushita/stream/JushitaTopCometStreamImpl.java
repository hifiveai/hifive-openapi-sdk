package com.hifive.api.internal.jushita.stream;


import com.hifive.api.internal.jushita.JushitaConfigurationV2;
import com.hifive.api.internal.stream.TopCometStreamImpl;
import com.hifive.link.Logger;
import com.hifive.link.logging.LogUtil;

/**
 * Created by IntelliJ IDEA.
 * User: guichen - anson
 * Date: 12-8-21
 */
public class JushitaTopCometStreamImpl extends TopCometStreamImpl {
	private static final Logger logger = LogUtil.getLoggerFactory(JushitaTopCometStreamImpl.class).create(JushitaTopCometStreamImpl.class);

	private JushitaConfigurationV2 configuration;

	public JushitaTopCometStreamImpl(JushitaConfigurationV2 configuration) {
		super(configuration);
		this.configuration = configuration;
	}

	@Override
	public void start() {
		super.start();
		if (configuration.getTopCometMessageListener() == null) {
			throw new NullPointerException("Comet message listener must not null");
		}
		configuration.getDriver().setReportCount(configuration.getReportCount());
		configuration.getDriver().setReportInterval(configuration.getReportInterval());
		configuration.getDriver().start();
		logger.warn("stream start:" + configuration);
	}

	@Override
	public void stop() {
		super.stop();
		configuration.getDriver().stop();
		logger.warn("stream stop:" + configuration);
	}
}
