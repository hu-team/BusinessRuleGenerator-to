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

            }


        } catch(Exception e){
            e.printStackTrace();
        }

        return values;
    }
}
