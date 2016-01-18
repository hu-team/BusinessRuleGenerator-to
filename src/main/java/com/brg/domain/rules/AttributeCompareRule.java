package com.brg.domain.rules;

import com.brg.ServiceProvider;
import com.brg.analyse.DatabaseService;
import com.brg.domain.BusinessRule;


public class AttributeCompareRule extends BusinessRule{

    public AttributeCompareRule() {
        super();
    }

    @Override
    public boolean validateRule() {
        String table = "", column = "";
        DatabaseService databaseService = ServiceProvider.getInstance().getAnalyseService().getDatabaseService();

        for(String key: this.getValues().getKeys()) {
            if(key.equals("attribute.column")) {

                column = (String) this.getValues().getValue(key);
            }

            if(key.equals("attribute.table")){
                table = (String) this.getValues().getValue(key);
            }
        }

        if(databaseService.hasTable(table.toUpperCase()) && databaseService.hasColumn(table.toUpperCase(), column.toUpperCase())) {
            return true;
        }

        return false;
    }
}
