package com.brg.analyse;

import com.brg.ServiceProvider;
import com.brg.dao.connection.TargetConnection;
import javafx.util.Callback;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.apache.ddlutils.platform.JdbcModelReader;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;

public class TargetDatabaseTask implements Runnable {
    private final Callback callback;

    /**
     * @see {http://db.apache.org/ddlutils/index.html} Reference to docs
     */
    public TargetDatabaseTask(Callback callback) {
        this.callback = callback;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        System.out.println("Indexer: Starting Index Target database");

        System.out.println("Indexer: Connecting to target database...");

        try{
            // Clear all in the service
            DatabaseService service = ServiceProvider.getInstance().getAnalyseService().getDatabaseService();
            service.clearAll();

            // Connect and fetch
            TargetConnection targetConnection = ServiceProvider.getInstance().getDaoService().getTargetConnection();
            DataSource dataSource = targetConnection.getDataSource();

            System.out.println("Indexer: Reading database...");
            Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);
            JdbcModelReader modelReader = platform.getModelReader();

            Database database = modelReader.getDatabase(targetConnection.getConnection(), targetConnection.getSchema(), null, targetConnection.getSchema(), null);
            service.setDatabase(database);


            System.out.println("Indexer: Reading tables and columns...");
            // Tables
            for(Table table : database.getTables()) {
                String tableName = table.getName();

                // Add table to list
                service.getTables().put(tableName, table);

                // Add columns
                ArrayList<Column> columns = new ArrayList<Column>();
                Collections.addAll(columns, table.getColumns());

                // Add to the service
                service.putColumns(table, columns);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done: Indexed Target database");

        // Get on main thread and call the callback.
        javafx.application.Platform.runLater(() -> callback.call(null));
    }
}
