package com.brg.dao;

import com.brg.dao.connection.RepositoryConnection;
import com.brg.dao.connection.TargetConnection;

public interface DaoService {

    BusinessRuleDAO getBusinessRuleDAO();
    RuleValueBundleDAO getRuleValueBundleDAO();
    RuleOperandDAO getRuleOperandDAO();

    RepositoryConnection getRepositoryConnection() throws Exception;
    TargetConnection getTargetConnection() throws Exception;

    void clearConnections();
}
