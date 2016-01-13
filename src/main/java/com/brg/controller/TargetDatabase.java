package com.brg.controller;

import com.brg.dao.connection.TargetConnection;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.apache.ddlutils.platform.JdbcModelReader;

import javax.sql.DataSource;
import java.util.HashMap;

public class TargetDatabase {

    private TargetConnection targetConnection;
    private HashMap<String, String[]> targetDatabase;
    private Database _tempdatabase;


    /**
     * @see {http://db.apache.org/ddlutils/index.html} Reference to docs
     */
    public TargetDatabase() {
        targetDatabase = new HashMap<String, String[]>();
    }

    public void setTargetDatabase() {
        System.out.println("Start: Index Target database");
        int len = 0;
        try {

            //Set database in memory for loading speed
            _tempdatabase = this.getDatabase();

            //Loop over tables
            for(Table t : this.getTables()) {
                String tableName = t.getName();
                System.out.println(tableName);
                int columnlen = this.getColumns(len).length;
                int currcolum = 0;
                String[] columns = new String[columnlen];

                //Loop over columns in table
                for(Column cmn : this.getColumns(len)) {

                    //Put column name in string array
                    columns[currcolum] = cmn.getName();
                    currcolum++;
                }

                //Put table with columns in hashmap
                targetDatabase.put(tableName, columns);

                len++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Clear memory
        _tempdatabase = null;

        System.out.println("Done: Indexed Target database");

    }

    /**
     * Get target database
     * @return Database
     * @throws Exception
     */
    public Database getDatabase() throws Exception {
        targetConnection = targetConnection.getInstance();
        DataSource dataSource = targetConnection.getDataSource();

        Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);
        JdbcModelReader modelReader = platform.getModelReader();
        Database database = modelReader.getDatabase(targetConnection.getConnection(), targetConnection.getSchema(), null, targetConnection.getSchema(), null);

        return database;
    }

    /**
     * Get target tables
     * @return Table[]
     * @throws Exception
     */
    private Table[] getTables() throws Exception {
        Table[] tables = _tempdatabase.getTables();

        return tables;
    }

    private Table getTable(int i) throws Exception {
        Table table = _tempdatabase.getTable(i);
        return table;
    }

    private Column[] getColumns(int tableId) throws Exception {
        Column[] columns = _tempdatabase.getTable(tableId).getColumns();
        return columns;
    }
}
