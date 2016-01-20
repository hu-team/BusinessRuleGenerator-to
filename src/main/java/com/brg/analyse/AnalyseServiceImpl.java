package com.brg.analyse;


import com.brg.common.AbstractFacadeService;
import com.brg.domain.RuleValueBundle;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;

import java.util.List;

/**
 * Analyse Public Facade Service
 */
public class AnalyseServiceImpl extends AbstractFacadeService implements AnalyseService {
    private DatabaseService databaseService = new DatabaseService();

    @Override
    public void willExitApplication() {
        this.databaseService.willExitApplication();
    }

    @Override
    public void startIndexingTargetDatabase() {
        this.databaseService.startIndexingTargetDatabase();
    }



    @Override
    public void clearAllTargetMetadata() {
        this.databaseService.clearAll();
    }

    @Override
    public void setTargetDatabaseMetadata(Database database) {
        this.databaseService.setDatabase(database);
    }

    @Override
    public void addTargetTable(String tableName, Table table) {
        this.databaseService.getTables().put(tableName, table);
    }

    @Override
    public void addTargetColumns(Table table, List<Column> columns) {
        this.databaseService.getColumns().put(table, columns);
    }

    @Override
    public DatabaseService getDatabaseService() {
        return databaseService;
    }


    @Override
    public boolean validateRule(RuleValueBundle bundle, String ruleType) {
        ValidateRuleHelper validateRuleHelper = new ValidateRuleHelper(bundle);
        return validateRuleHelper.validateType(ruleType);
    }
}
