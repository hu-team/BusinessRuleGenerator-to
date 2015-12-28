package com.brg.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TargetConnection {

    private TargetConnection instance;

    private static String host;
    private static String schema;
    private static String username;
    private static String password;

    public TargetConnection getInstance(){
        if(instance == null){
            instance = new TargetConnection();
        }

        return instance;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(host + schema, username, password);
    }

    public static void setHost(String h){
        host = h;
    }

    public static void setSchema(String s){
        schema = s;
    }

    public static void setUsername(String u){
        username = u;
    }

    public static void setPassword(String p){
        password = p;
    }

}
