package com.brg.analyse;

import com.brg.controller.TargetDatabaseThread;

public class DatabaseService {


    public DatabaseService()  {
        TargetDatabaseThread targetDatabaseThread = new TargetDatabaseThread();
        targetDatabaseThread.start();
    }

}
