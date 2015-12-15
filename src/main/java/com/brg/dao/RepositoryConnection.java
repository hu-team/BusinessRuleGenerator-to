package com.brg.dao;

/**
 * Developed by Arjan.
 */
public class RepositoryConnection implements DatabaseConnection{

    private RepositoryConnection instance;

    public RepositoryConnection getInstance(){
        if(instance == null) {
            instance = new RepositoryConnection();
        }

        return instance;
    }

}
