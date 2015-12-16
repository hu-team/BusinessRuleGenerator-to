package com.brg.dao.connection;

/**
 * Developed by Arjan.
 */
public class TargetConnection implements DatabaseConnection {

    private TargetConnection instance;

    public TargetConnection getInstance(){
        if(instance == null){
            instance = new TargetConnection();
        }

        return instance;
    }

}
