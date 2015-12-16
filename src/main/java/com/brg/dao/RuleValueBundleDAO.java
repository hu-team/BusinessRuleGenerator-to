package com.brg.dao;


import com.brg.ServiceProvider;
import com.brg.domain.RuleValueBundle;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RuleValueBundleDAO implements DAO{

    @Override
    public ArrayList<RuleValueBundle> executeRead(String sql) {
        ArrayList<RuleValueBundle> values = new ArrayList<RuleValueBundle>();

        try{
            Statement stmt = ServiceProvider.getInstance().getPersistanceService().getConnection().createStatement();
            ResultSet set = stmt.executeQuery(sql);

            while(set.next()) {
                RuleValueBundle bundle = new RuleValueBundle();

                Statement valueStmt = ServiceProvider.getInstance().getPersistanceService().getConnection().createStatement();
                ResultSet valueSet = valueStmt
                        .executeQuery("SELECT Key FROM BundleKeyEntry WHERE BundleID=" + set.getInt("BundleID"));
                
                bundle.setValue(valueSet.getString("Key"), null);
            }


        } catch(Exception e){
            e.printStackTrace();
        }

        return values;
    }

    public RuleValueBundle getRuleValueBundleById(int id){

        return null;
    }

    public ArrayList<RuleValueBundle> getAllRuleValueBundle(){
        return this.executeRead("SELECT * FROM Rule;");
    }
}
