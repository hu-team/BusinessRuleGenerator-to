package com.brg.controller;


public class TargetDatabaseThread extends Thread {

    public void run() {
        TargetDatabase targetDatabase = new TargetDatabase();
        targetDatabase.setTargetDatabase();

    }

}
