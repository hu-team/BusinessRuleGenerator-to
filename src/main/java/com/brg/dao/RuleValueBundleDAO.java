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
                        .executeQuery("SELECT bke.Key, ekv.String_Value, ekv.Int_Value, ekv.Float_Value" +
                                "FROM BundleKeyEntry bke, EntryKeyValue ekv" +
                                "WHERE bke.BundleID=" + set.getInt("BundleID") + " AND " +
                                "ekv.EntryID = bke.EntryID" );

                while(valueSet.next()) {
                    String keyName = valueSet.getString("Key");

                    Integer intValue = valueSet.getInt("Int_Value");
                    if(!valueSet.wasNull()){
                        bundle.setValue(keyName, intValue);
                    }

                    Float floatValue = valueSet.getFloat("Float_Value");
                    if(!valueSet.wasNull()){
                        bundle.setValue(keyName, floatValue);
                    }

                    if(valueSet.getString("String_Value") != null){
                        bundle.setValue(keyName, valueSet.getString("String_Value"));
                    }


                }
                valueSet.close();
                valueStmt.close();

            }

            set.close();
            stmt.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return values;
    }

    public RuleValueBundle getRuleValueBundleById(int id){
        ArrayList<RuleValueBundle> values = new ArrayList<RuleValueBundle>(); //temp
        return values.size() >= 1 ? values.get(0) : null;
    }

    public ArrayList<RuleValueBundle> getAllRuleValueBundle(){
        return this.executeRead("SELECT * FROM Rule;");
    }
}
