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
        }

        return value;
    }

    private boolean EntityValidate() {
        return false;
    }

    private boolean TupleValidate() {
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
