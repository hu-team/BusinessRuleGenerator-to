package com.brg.controller;

public interface ControllerServiceImpl {
    MainWindow getMainWindow();
    DefineTab getDefineTab();

    void setLoadingProgress(double percentage, String text);
}
