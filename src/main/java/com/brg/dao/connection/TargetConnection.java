package com.brg.dao.connection;

import com.brg.common.Config;
import com.brg.domain.DatabaseType;

import java.sql.*;
import java.util.Properties;


public class TargetConnection implements DatabaseConnection {

    private static TargetConnection instance;

    private static String host;
    private static String service;
    private static int port;
    private static String schema;
    private static String username;
    private static String password;
    private static DatabaseType type;

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
