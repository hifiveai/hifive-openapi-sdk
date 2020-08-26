package com.hifive.link.logging;

//import org.apache.log4j.ConsoleAppender;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.PatternLayout;


import com.hifive.link.DefaultLoggerFactory;
import com.hifive.link.LoggerFactory;

public class LogUtil {
	static {
		// LogManager.getRootLogger().addAppender(new ConsoleAppender(new
		// PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN)));
	}

	public static LoggerFactory getLoggerFactory(Object obj) {
		LoggerFactory loggerFactory = null;

		ClassLoader current = obj.getClass().getClassLoader();

		if (loggerFactory == null) {
			try {
				Class.forName("org.apache.log4j.LogManager", false, current);
				loggerFactory = new Log4jLoggerFactory();
			} catch (ClassNotFoundException e) {
			}
		}
		if (loggerFactory == null) {
			try {
				Class.forName("org.apache.commons.logging.Log", false, current);
				loggerFactory = new CommonsLoggerFactory();
			} catch (ClassNotFoundException e) {
			}
		}
		if (loggerFactory == null)
			loggerFactory = DefaultLoggerFactory.getDefault();

		return loggerFactory;
	}
}
