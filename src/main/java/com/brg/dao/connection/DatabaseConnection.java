package com.brg.dao.connection;

import java.sql.*;


public interface DatabaseConnection {

    public Connection getConnection() throws SQLException, Exception;

    public ResultSet select(PreparedStatement preparedStatement) throws SQLException;
    public ResultSet select(Statement statement) throws SQLException;

}
