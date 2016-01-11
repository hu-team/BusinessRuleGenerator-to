package com.brg.persistence;


import com.brg.common.AbstractFacadeService;

import java.sql.*;

public class PersistenceService extends AbstractFacadeService implements PersistenceServiceImpl {

    private Connection connection;

    private BusinessRuleService businessRuleService = new BusinessRuleService();
    private RuleValueBundleService ruleValueBundleService = new RuleValueBundleService();
    private RuleOperandService ruleOperandService = new RuleOperandService();

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

}
