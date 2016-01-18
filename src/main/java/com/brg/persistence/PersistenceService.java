package com.brg.persistence;

public interface PersistenceService {
    BusinessRuleService getBusinessRuleService();

    RuleValueBundleService getRuleValueBundleService();

    RuleOperandService getRuleOperandService();

    TemplateService getTemplateService();
}
