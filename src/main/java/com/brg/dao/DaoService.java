package com.brg.dao;

import com.brg.common.AbstractFacadeService;

public class DaoService extends AbstractFacadeService implements DaoServiceImpl {

    private BusinessRuleDAO businessRuleDAO = new BusinessRuleDAO();
    private RuleValueBundleDAO ruleValueBundleDAO = new RuleValueBundleDAO();


    @Override
    public BusinessRuleDAO getBusinessRuleDAO() {
        return this.businessRuleDAO;
    }

    @Override
    public RuleValueBundleDAO getRuleValueBundleDAO() {
        return this.ruleValueBundleDAO;
    }
}
