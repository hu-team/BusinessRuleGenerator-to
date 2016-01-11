package com.brg.dao.connection;

import com.brg.common.Config;
import com.brg.domain.DatabaseType;

import java.sql.*;
import java.util.Properties;


public class TargetConnection implements DatabaseConnection {

    private static TargetConnection instance;

    private final String host;
    private final String service;
    private final int port;
    private final String schema;
    private final String username;
    private final String password;
    private final DatabaseType type;

    private Connection connection;

    /**
     * Get Target Connection Instance
     * @return TargetConnection
     * @throws Exception
     */
    public static TargetConnection getInstance() throws Exception {
        if(instance == null) {
            instance = new TargetConnection();
        }

        return instance;
    }

    /**
     * Clear connection instances
     */
    public static void clearConnection() {
        instance = null;
    }

    private TargetConnection() throws Exception {
        // Load database config from Config class.
        Properties properties = Config.getInstance().getConfig();

        // Verify and insert into repository connection class.
        host = properties.getProperty("target_host");
        schema = properties.getProperty("target_schema");
        username = properties.getProperty("target_username");
        password = properties.getProperty("target_password");
        service = properties.getProperty("target_service");
        port = Integer.parseInt(properties.getProperty("target_port"));

        String typeString = properties.getProperty("target_type");

        if ("ORACLE".equals(typeString)) {
            type = DatabaseType.ORACLE;
        }else{
            throw new Exception("No driver found for the target database!");
        }
    }

    /**
     * Get connection
     * @return Connection
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        if (connection == null) {
            // Prepare
            String connectString = "";

            // Driver independent
            if (type == DatabaseType.ORACLE) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connectString = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + service;
            }

            // Make connection
            connection = DriverManager.getConnection(connectString, username, password);
        }
        return connection;
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
