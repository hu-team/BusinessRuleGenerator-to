package com.brg.analyse;

import com.brg.domain.RuleValueBundle;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;

import java.util.List;

public interface AnalyseService {

    void startIndexingTargetDatabase();
    void clearAllTargetMetadata();

    void setTargetDatabaseMetadata(Database database);
    void addTargetTable(String tableName, Table table);
    void addTargetColumns(Table table, List<Column> columns);
    DatabaseService getDatabaseService();

    boolean validateRule(RuleValueBundle bundle, String ruleType);
}
