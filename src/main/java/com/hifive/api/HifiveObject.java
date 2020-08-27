package com.hifive.api;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Base Data Structure.
 *
 * @author carver.gu
 * @since 1.0, Apr 11, 2010
 */
public abstract class HifiveObject<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
