package com.brg.analyse;

import com.brg.ServiceProvider;
import javafx.util.Callback;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseService implements Callback {
    private TargetDatabaseTask targetDatabaseTask;
    private Thread targetDatabaseThread;

    private Database database;
    private HashMap<String, Table> tables;
    private HashMap<Table, List<Column>> columns;

    public DatabaseService()  {
        this.targetDatabaseTask = new TargetDatabaseTask(this);

        this.database = null;
        this.tables = new HashMap<>();
        this.columns = new HashMap<>();
    }

    /**
     * Start indexing target db
     */
    public void startIndexingTargetDatabase() {
        this.targetDatabaseThread = new Thread(this.targetDatabaseTask);
        ServiceProvider.getInstance().getControllerService().setLoadingProgress(-1.0, "Loading target database structure...");
        this.targetDatabaseThread.start();
    }

    @Override
    public Object call(Object param) {
        ServiceProvider.getInstance().getControllerService().setLoadingProgress(0.0, "");
        return null;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public HashMap<String, Table> getTables() {
        return tables;
    }

    public HashMap<Table, List<Column>> getColumns() {
        return columns;
    }

    public boolean hasTable(String tableName) {
        return this.tables.containsKey(tableName);
    }

    public boolean hasColumn(String tableName, String columnName) {
        if (this.hasTable(tableName) && this.columns.containsKey(this.tables.get(tableName))) {
            for(Column column: this.getColumns(tableName)) {
                if(columnName.equals(column.getName())) {
                    return true;
                }
            }

        }

        return false;
    }

    public List<Column> getColumns(String table) {
        if (this.tables.containsKey(table)) {
            return this.columns.get(this.tables.get(table));
        }
        return null;
    }

    public void clearAll() {
        this.database = null;
        this.tables = new HashMap<>();
        this.columns = new HashMap<>();
    }

    public void putColumns(Table table, ArrayList<Column> columns) {
        this.columns.put(table, columns);
    }

    public void willExitApplication() {
        //noinspection deprecation
        this.targetDatabaseThread.interrupt();
    }
}
