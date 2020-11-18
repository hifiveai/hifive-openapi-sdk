package com.hifive.link.logging;

import com.hifive.link.Logger;
import com.hifive.link.LoggerFactory;
import org.apache.commons.logging.impl.LogFactoryImpl;

public class CommonsLoggerFactory implements LoggerFactory {
    @Override
    public Logger create(String type) {
        return new CommonsLogger(LogFactoryImpl.getLog(type));
    }

    @Override
    public Logger create(Class<?> type) {
        return new CommonsLogger(LogFactoryImpl.getLog(type));
    }

    @Override
    public Logger create(Object object) {
        return new CommonsLogger(LogFactoryImpl.getLog(object.getClass()));
    }
}
