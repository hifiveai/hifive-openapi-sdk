package com.hifive.link.logging;

import com.hifive.link.Logger;
import com.hifive.link.LoggerFactory;
import org.apache.logging.log4j.LogManager;

public class Log4jLoggerFactory implements LoggerFactory {

    @Override
    public Logger create(String type) {
        return new Log4jLogger(LogManager.getLogger(type));
    }

    @Override
    public Logger create(Class<?> type) {
        return new Log4jLogger(LogManager.getLogger(type));
    }

    @Override
    public Logger create(Object object) {
        return new Log4jLogger(LogManager.getLogger(object.getClass()));
    }

}
