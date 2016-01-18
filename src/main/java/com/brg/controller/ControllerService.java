package com.brg.controller;

public interface ControllerService {
    MainWindow getMainWindow();
    DefineTab getDefineTab();

    void setLoadingProgress(double percentage, String text);
}
