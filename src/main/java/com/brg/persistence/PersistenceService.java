package com.brg.persistence;

import com.brg.common.AbstractFacadeService;

public class PersistenceService extends AbstractFacadeService implements PersistenceServiceImpl {

    private BusinessRuleService businessRuleService = new BusinessRuleService();
    private RuleValueBundleService ruleValueBundleService = new RuleValueBundleService();
    private RuleOperandService ruleOperandService = new RuleOperandService();
    private TemplateService templateService = new TemplateService();

    @Override
    public BusinessRuleService getBusinessRuleService() {
        return this.businessRuleService;
    }

    @Override
    public RuleValueBundleService getRuleValueBundleService() {
        return this.ruleValueBundleService;
    }

    @Override
    public RuleOperandService getRuleOperandService() {
        return this.ruleOperandService;
    }

    @Override
    public TemplateService getTemplateService() {
        return this.templateService;
    }

    @Override
    public void willExitApplication() {

    }
}
