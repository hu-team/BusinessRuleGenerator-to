package com.brg.dao;

import com.brg.common.AbstractFacadeService;
import com.brg.dao.connection.RepositoryConnection;
import com.brg.dao.connection.TargetConnection;
import com.brg.domain.BusinessRule;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;

import java.sql.Statement;
import java.util.ArrayList;

public class DaoServiceImpl extends AbstractFacadeService implements DaoService {

    private BusinessRuleDAO businessRuleDAO = new BusinessRuleDAO();
    private RuleValueBundleDAO ruleValueBundleDAO = new RuleValueBundleDAO();
    private RuleOperandDAO ruleOperandDAO = new RuleOperandDAO();

    @Override
    public RepositoryConnection getRepositoryConnection() throws Exception { return RepositoryConnection.getInstance(); }

    @Override
    public TargetConnection getTargetConnection() throws Exception { return TargetConnection.getInstance(); }

    @Override
    public void clearConnections() {
        RepositoryConnection.clearConnection();
        TargetConnection.clearConnection();
    }


    @Override
    public ArrayList<BusinessRule> getAllBusinessRules() {
        return this.businessRuleDAO.getAllRules();
    }

    @Override
    public RuleValueBundle getRuleValueBundleForRule(int ruleID) {
        return this.ruleValueBundleDAO.getRuleValueBundleById(ruleID);
    }

    @Override
    public RuleOperand getRuleOperandById(int id) {
        return this.ruleOperandDAO.getRuleOperandById(id);
    }

    @Override
    public void applyQueryToTarget(String output) throws Exception {
        TargetConnection connection = this.getTargetConnection();
        Statement statement = connection.getConnection().createStatement();

        output = output.replaceAll("\r\n", "\n");

        statement.executeUpdate(output);

        statement.close();
    }

}
