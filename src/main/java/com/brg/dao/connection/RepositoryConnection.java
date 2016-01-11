package com.brg.dao.connection;

import com.brg.ServiceProvider;
import com.brg.common.Config;
import com.brg.domain.DatabaseType;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class RepositoryConnection implements DatabaseConnection {

    private static RepositoryConnection instance;

    private static String host;
    private static String service;
    private static int port;
    private static String schema;
    private static String username;
    private static String password;

    private static DatabaseType type;

    private Connection connection;

    /**
     * Get Repository Connection Instance
     * @return RepositoryConnection
     * @throws Exception
     */
    public static RepositoryConnection getInstance() throws Exception {
        if(instance == null) {
            instance = new RepositoryConnection();
        }

        return instance;
    }

    /**
     * Clear connection instances
     */
    public static void clearConnection() {
        instance = null;
    }

    private RepositoryConnection() throws Exception {
        // Load database config from Config class.
        Properties properties = Config.getInstance().getConfig();

        // Verify and insert into repository connection class.
        host = properties.getProperty("repository_host");
        schema = properties.getProperty("repository_schema");
        username = properties.getProperty("repository_username");
        password = properties.getProperty("repository_password");
        service = properties.getProperty("repository_service");
        port = Integer.parseInt(properties.getProperty("repository_port"));

        type = DatabaseType.ORACLE;
    }

    /**
     * Get connection
     * @return Connection
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        if (connection == null) {
            // Prepare
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String connectString = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + service;

            // Make connection
            connection = DriverManager.getConnection(connectString, username, password);
        }
        return DriverManager.getConnection(host + schema, username, password);
    }

    @Override
    public ResultSet select(PreparedStatement preparedStatement) throws SQLException {
        return null;
    }

    @Override
    public ResultSet select(Statement statement) throws SQLException {
        return null;
    }
}
