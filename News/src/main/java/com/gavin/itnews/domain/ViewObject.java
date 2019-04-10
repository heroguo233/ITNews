package com.gavin.itnews.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gavin
 * on 2019/4/10 15:20
 */
public class ViewObject {
    private Map<String, Object> objs = new HashMap<String, Object>();
    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public Object get(String key) {
        return objs.get(key);
    }
}
