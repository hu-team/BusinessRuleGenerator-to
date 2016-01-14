package com.brg.controller;

public interface ControllerServiceImpl {
    MainWindow getMainWindow();
    DefineTab getDefineTab();

    SplashWindow getSplashWindow();

    void setLoadingProgress(double percentage);
}
