package com.brg.domain;

import java.util.ArrayList;

/**
 * Developed by Arjan.
 */
public class Category {

    private String sign;

    private ArrayList<BusinessRule> rules;

    public Category(String s) {
        this.sign = s;
        this.rules = new ArrayList<BusinessRule>();
    }

    public String getSign() {
        return this.sign;
    }

    public ArrayList<BusinessRule> getRules() {
        return this.rules;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void addRule(BusinessRule br) {
        this.rules.add(br);
    }
}
