package com.brg.persistence;

import java.sql.Connection;

public interface PersistenceServiceImpl {
    BusinessRuleService getBusinessRuleService();

    RuleValueBundleService getRuleValueBundleService();


    // Only for prototype
    // TODO: refactor
    Connection getConnection();
}
