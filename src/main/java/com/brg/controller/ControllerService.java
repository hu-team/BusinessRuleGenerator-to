package com.brg.controller;

import com.brg.common.AbstractFacadeService;


public class ControllerService extends AbstractFacadeService implements ControllerServiceImpl {

    private MainWindow mainWindow;
    private DefineTab defineTab;

    private SplashWindow splashWindow;

    public ControllerService() {
        this.mainWindow = new MainWindow();
        this.defineTab = new DefineTab();
    }

    @Override
    public MainWindow getMainWindow() {
        if (MainWindow.getInstance() == null) {
            return this.mainWindow;
        }
        return MainWindow.getInstance();
    }

    @Override
    public DefineTab getDefineTab() {
        return this.defineTab;
    }


    public SplashWindow getSplashWindow() {
        return this.getMainWindow().getSplashWindow();
    }
}
