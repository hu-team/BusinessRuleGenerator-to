package com.brg.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;


public interface DatabaseConnection {

    public Connection getConnection() throws SQLException;

    //TODO: De static methods moeten nog in de interface komen maar ?????

}
