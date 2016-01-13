package com.brg.analyse;

import com.brg.controller.TargetDatabaseThread;

public class DatabaseService {
    private TargetDatabaseThread targetDatabaseThread;

    public DatabaseService()  {
        targetDatabaseThread = new TargetDatabaseThread();
    }

    /**
     * Start indexing target db
     */
    public void startIndexingTargetDatabase() {
        this.targetDatabaseThread.start();
    }

    public void stopTargetDatabaseThread() {
        System.out.println("Stop Thread");
        targetDatabaseThread.interrupt();
    }

}
