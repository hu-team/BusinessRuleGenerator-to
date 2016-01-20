package com.brg.analyse;

import com.brg.ServiceProvider;
import com.brg.domain.RuleValueBundle;

public class ValidateRuleHelper {
    private  DatabaseService databaseService;
    private RuleValueBundle ruleValueBundle;

    public ValidateRuleHelper(RuleValueBundle ruleValueBundle) {
        databaseService = ServiceProvider.getInstance().getAnalyseService().getDatabaseService();
        this.ruleValueBundle = ruleValueBundle;
    }

    /**
    * Check which type must by validate
     **/
    public boolean RuleType(String type) {
        boolean value = false;

        switch (type) {
            case "Attribute": value = AttributeValidate();
                break;
            case "Tuple": value = TupleValidate();
                break;
            case "Entity": value = EntityValidate();
                break;
            case "InterEntity": value = InterEntityValidate();
                break;
        }

        return value;
    }

    private boolean InterEntityValidate() {
        String column1 = "", column2 = "", table1 = "", table2 = "";

        for(String key: ruleValueBundle.getKeys()) {
            if(key.equals("interentity.table.1")) {
                table1 = (String) ruleValueBundle.getValue(key);
            }

            if(key.equals("interentity.table.2")) {
                table2 = (String) ruleValueBundle.getValue(key);
            }

            if(key.equals("interentity.column.1")) {
                column1 = (String) ruleValueBundle.getValue(key);
            }

            if(key.equals("interentity.column.2")) {
                column2 = (String) ruleValueBundle.getValue(key);
            }
        }

        if(databaseService.hasTable(table1.toUpperCase()) && databaseService.hasTable(table2.toUpperCase()) && databaseService.hasColumn(table1.toUpperCase(), column1.toUpperCase()) && databaseService.hasColumn(table2.toUpperCase(), column2.toUpperCase())) {
            return true;
        }

        return false;
    }

    private boolean EntityValidate() {
        String table = "", column = "";
        
        for(String key: ruleValueBundle.getKeys()) {
            if(key.equals("entity.table")) {
                table = (String) ruleValueBundle.getValue(key);
            }

            if(key.equals("entity.column")) {
                column = (String) ruleValueBundle.getValue(key);
            }
        }

        if(databaseService.hasTable(table.toUpperCase()) && databaseService.hasColumn(table.toUpperCase(), column.toUpperCase())) {
            return true;
        }

        return false;
    }

    private boolean TupleValidate() {
        String table = "", column1 = "", column2 = "";

        for(String key: ruleValueBundle.getKeys()) {
            if(key.equals("tuple.table")) {
                table = (String) ruleValueBundle.getValue(key);
            }

            if(key.equals("tuple.column1")) {
                column1 = (String) ruleValueBundle.getValue(key);
            }

            if(key.equals("tuple.column2")) {
                column2 = (String) ruleValueBundle.getValue(key);
            }
        }

        if(databaseService.hasTable(table.toUpperCase()) && databaseService.hasColumn(table.toUpperCase(), column1.toUpperCase()) && databaseService.hasColumn(table.toUpperCase(), column2.toUpperCase())) {
            return true;
        }

        return false;
    }

    private boolean AttributeValidate() {
        String table = "", column = "";

        for(String key: ruleValueBundle.getKeys()) {
            if(key.equals("attribute.column")) {

                column = (String) ruleValueBundle.getValue(key);
            }

            if(key.equals("attribute.table")){
                table = (String) ruleValueBundle.getValue(key);
            }
        }

        if(databaseService.hasTable(table.toUpperCase()) && databaseService.hasColumn(table.toUpperCase(), column.toUpperCase())) {
            return true;
        }

        return false;
    }

}
