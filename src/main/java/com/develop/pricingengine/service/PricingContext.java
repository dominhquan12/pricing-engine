package com.develop.pricingengine.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PricingContext {

    private final Map<String, Object> params = new HashMap<>();

    public void put(String key, Object value) {
        params.put(key, value);
    }

    public Object get(String key) {
        return params.get(key);
    }

    public boolean contains(String key) {
        return params.containsKey(key);
    }

    public Map<String, Object> asMap() {
        return Collections.unmodifiableMap(params);
    }
}

