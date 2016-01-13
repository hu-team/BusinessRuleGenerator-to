package com.brg.analyse;

import com.brg.controller.TargetDatabaseThread;

public class DatabaseService {
    private TargetDatabaseThread targetDatabaseThread;

    public DatabaseService()  {
        targetDatabaseThread = new TargetDatabaseThread();
        targetDatabaseThread.start();
    }

    public void stopTargetDatabaseThread() {
        System.out.println("Stop Thread");
        targetDatabaseThread.interrupt();
    }

}
