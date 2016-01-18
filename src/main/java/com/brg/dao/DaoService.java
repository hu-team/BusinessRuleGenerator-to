package com.brg.dao;

import com.brg.dao.connection.RepositoryConnection;
import com.brg.dao.connection.TargetConnection;
import com.brg.domain.BusinessRule;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;

import java.util.ArrayList;

public interface DaoService {

    RepositoryConnection getRepositoryConnection() throws Exception;
    TargetConnection getTargetConnection() throws Exception;

    void clearConnections();

    ArrayList<BusinessRule> getAllBusinessRules();
    RuleValueBundle getRuleValueBundleForRule(int ruleID);
    RuleOperand getRuleOperandById(int id);
}
