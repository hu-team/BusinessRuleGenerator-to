package com.brg.domain;


public abstract class BusinessRule{

    private String code;
    private String name;
    private String description;
    private String error;

    private Category category;
    private RuleOperand operand;
    private RuleValueBundle values;

    public BusinessRule() {}

    public abstract boolean validateRule();

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOperand(RuleOperand operand) {
        this.operand = operand;
    }

    public void setValues(RuleValueBundle values) {
        this.values = values;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public RuleOperand getOperand() {
        return operand;
    }

    public RuleValueBundle getValues() {
        return values;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return this.getCode() + " - " + this.getName();
    }
}
