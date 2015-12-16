package com.brg.dao;


import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;

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

            }

        }catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public ArrayList<BusinessRule> getAllRules() {
        return this.executeRead("SELECT * FROM Rule");
    }
}
