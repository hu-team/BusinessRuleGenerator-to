package com.brg.persistence;

public interface PersistenceServiceImpl {
    BusinessRuleService getBusinessRuleService();

    RuleValueBundleService getRuleValueBundleService();

    RuleOperandService getRuleOperandService();

    TemplateService getTemplateService();
}
