package com.brg.persistence;

import java.sql.Connection;

public interface PersistenceServiceImpl {
    BusinessRuleService getBusinessRuleService();

    RuleValueBundleService getRuleValueBundleService();

    RuleOperandService getRuleOperandService();


    // Only for prototype
    // TODO: refactor
    Connection getConnection();
}
