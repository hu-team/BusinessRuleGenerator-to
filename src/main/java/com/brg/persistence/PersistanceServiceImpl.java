package com.brg.persistence;

import java.sql.Connection;

public interface PersistanceServiceImpl {
    BusinessRuleService getBusinessRuleService();

    RuleValueBundleService getRuleValueBundleService();


    // Only for prototype
    // TODO: refactor
    Connection getConnection();
}
