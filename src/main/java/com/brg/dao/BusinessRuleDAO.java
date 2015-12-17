package com.brg.dao;


import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;
import com.brg.domain.RuleOperand;

import java.lang.reflect.Constructor;
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
            Statement stmt = ServiceProvider.getInstance().getPersistenceService().getConnection().createStatement();
            ResultSet set = stmt.executeQuery(sql);

            while (set.next()) {
                // Make business rule
                BusinessRule rule = null;

                // Make rule based on code
                try {
                    String className = "com.brg.domain.rules." + set.getString("ClassName");
                    Class<?> classPosibility = Class.forName(className);
                    Constructor<?> constructor = classPosibility.getConstructor();
                    rule = (BusinessRule)constructor.newInstance(new Object[] {});
                }catch(Exception e) {
                    e.printStackTrace();
                }

                if (rule != null) {
                    // Set values
                    rule.setDescription(set.getString("Description"));
                    rule.setName(set.getString("RULENAME"));
                    rule.setCode(set.getString("CODE"));
                    //rule.setOperand(new RuleOperand(set.getString("RuleOperand")));

                    // Get all bundle contents
                    rule.setValues(ServiceProvider.getInstance().getPersistenceService().getRuleValueBundleService().getRuleById(set.getInt("BUSINESSRULEID")));

                    // Add to array
                    rules.add(rule);
                }
            }

            set.close();
            stmt.close();
        }catch (SQLException se) {
            se.printStackTrace();
        }


        return rules;
    }

    /**
     * Get all rules
     * @return
     */
    public ArrayList<BusinessRule> getAllRules() {
        return this.executeRead("SELECT * FROM BUSINESSRULE");
    }
}
