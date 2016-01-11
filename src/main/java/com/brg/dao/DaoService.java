package com.brg.dao;

import com.brg.common.AbstractFacadeService;
import com.brg.dao.connection.RepositoryConnection;
import com.brg.dao.connection.TargetConnection;

public class DaoService extends AbstractFacadeService implements DaoServiceImpl {

    private BusinessRuleDAO businessRuleDAO = new BusinessRuleDAO();
    private RuleValueBundleDAO ruleValueBundleDAO = new RuleValueBundleDAO();
    private RuleOperandDAO ruleOperandDAO = new RuleOperandDAO();


    @Override
    public BusinessRuleDAO getBusinessRuleDAO() {
        return this.businessRuleDAO;
    }

    @Override
    public RuleValueBundleDAO getRuleValueBundleDAO() {
        return this.ruleValueBundleDAO;
    }

    @Override
    public RuleOperandDAO getRuleOperandDAO() {
        return this.ruleOperandDAO;
    }

    @Override
    public RepositoryConnection getRepositoryConnection() throws Exception { return RepositoryConnection.getInstance(); }

    @Override
    public TargetConnection getTargetConnection() throws Exception { return TargetConnection.getInstance(); }

    @Override
    public void clearConnections() {
        RepositoryConnection.clearConnection();
        TargetConnection.clearConnection();
    }
}
