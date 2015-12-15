package com.brg.controller;

import com.brg.common.AbstractFacadeService;

public class ControllerService extends AbstractFacadeService implements ControllerServiceImpl {

    private MainWindow mainWindow;
    private DefineTab defineTab;

    public ControllerService() {
        this.mainWindow = new MainWindow();
        this.defineTab = new DefineTab();
    }

    @Override
    public MainWindow getMainWindow() {
        return this.mainWindow;
    }

    @Override
    public DefineTab getDefineTab() {
        return this.defineTab;
    }
}
