package com.brg.domain;

import java.util.HashMap;


public class RuleValueBundle {

    private HashMap<String, Object> values;

    public RuleValueBundle(){
        this.values = new HashMap<String, Object>();
    }

    public Object getValue(String key){
        return values.get(key);
    }

    public void setValue(String key, Object obj){
        values.replace(key, obj);
    }

    public void putValue(String key, Object obj){
        values.put(key, obj);
    }
}
