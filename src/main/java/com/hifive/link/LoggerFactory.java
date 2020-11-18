package com.hifive.link;

public interface LoggerFactory {
    public Logger create(String type);

    public Logger create(Class<?> type);

    public Logger create(Object object);
}
