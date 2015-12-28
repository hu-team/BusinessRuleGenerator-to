package com.brg.domain;


public class RuleOperand {

    private String sign;

    public RuleOperand(){

    };

    public RuleOperand(String s) {
        this.sign = s;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String s) {
        this.sign = s;
    }
}
