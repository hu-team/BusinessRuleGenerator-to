package com.brg.analyse;


import com.brg.ServiceProvider;
import com.brg.dao.connection.TargetConnection;
import com.brg.mock.TargetDatabaseMock;
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
    private TargetDatabaseMock targetDatabaseMock;

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
        targetDatabaseMock = new TargetDatabaseMock();

        try{
            // Clear all in the service
            AnalyseService service = ServiceProvider.getInstance().getAnalyseService();
            service.clearAllTargetMetadata();

            // Connect and fetch
            TargetConnection targetConnection = ServiceProvider.getInstance().getDaoService().getTargetConnection();
            DataSource dataSource = targetConnection.getDataSource();

            System.out.println("Indexer: Reading database...");
            Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);
            JdbcModelReader modelReader = platform.getModelReader();

            //Database database = modelReader.getDatabase(targetConnection.getConnection(), targetConnection.getSchema(), null, targetConnection.getSchema(), null);
            Database database = targetDatabaseMock.getDatabase();
            service.setTargetDatabaseMetadata(database);

            // Tables loop
            for(Table table : database.getTables()) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Done: Cancelled!");
                    return;
                }
                String tableName = table.getName();

                // Add table to list
                service.addTargetTable(tableName, table);

                // Add columns
                ArrayList<Column> columns = new ArrayList<Column>();
                Collections.addAll(columns, table.getColumns());

                // Add to the service
                service.addTargetColumns(table, columns);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done: Indexed Target database");

        // Get on main thread and call the callback.
        javafx.application.Platform.runLater(() -> callback.call(null));
    }
}
