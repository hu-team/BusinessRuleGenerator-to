package com.brg.dao;


import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;
import com.brg.domain.RuleValueBundle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BusinessRuleDAO implements DAO{


    @Override
    public ArrayList<BusinessRule> executeRead(String sql) {
        ArrayList<BusinessRule> rules = new ArrayList<BusinessRule>();

        // Get all rules, query
        try {
            Statement stmt = ServiceProvider.getInstance().getPersistanceService().getConnection().createStatement();
            ResultSet set = stmt.executeQuery(sql);

            while (set.next()) {
                // Make business rule
                BusinessRule rule;
                // TODO: Bepaal welke rule het is en maak die aan.

                // Make value bundle
                RuleValueBundle bundle = new RuleValueBundle();

                // Get all bundle contents
                // TODO: Call RuleValueBundleDAO (must be created first!)

                rule.setValues(bundle);

            }

        }catch (SQLException se) {
            se.printStackTrace();
        }


        return rules;
    }

    public ArrayList<BusinessRule> getAllRules() {
        return this.executeRead("SELECT * FROM Rule;");
    }
}
