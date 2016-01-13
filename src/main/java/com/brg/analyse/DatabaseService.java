package com.brg.analyse;


import com.brg.controller.TargetDatabase;
import com.brg.dao.connection.TargetConnection;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.apache.ddlutils.platform.JdbcModelReader;

import javax.sql.DataSource;

public class DatabaseService {

    private TargetConnection targetConnection;
    /**
     * @see {http://db.apache.org/ddlutils/index.html} Reference to docs
     */
    public DatabaseService()  {
        //targetConnection.getInstance();
        //System.out.println(this.getTable(1));
    }

    /**
     * Get target database
     * @return Database
     * @throws Exception
     */
    public Database getDatabase() throws Exception {
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
    public Table[] getTables() throws Exception {
        Table[] tables = this.getDatabase().getTables();

        return tables;
    }

    public Table getTable(int i) throws Exception {
        Table table = this.getDatabase().getTable(i);
        return table;
    }

    public Column[] getColumns(int tableId) throws Exception {
        Column[] columns = this.getDatabase().getTable(tableId).getColumns();
        return columns;
    }

}
