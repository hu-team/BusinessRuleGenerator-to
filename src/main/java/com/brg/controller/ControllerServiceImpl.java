package com.brg.controller;

import com.brg.common.AbstractFacadeService;


public class ControllerServiceImpl extends AbstractFacadeService implements ControllerService {

    private MainWindow mainWindow;
    private DefineTab defineTab;

    public ControllerServiceImpl() {
        this.mainWindow = new MainWindow();
        this.defineTab = new DefineTab();
    }

    @Override
    public void setLoadingProgress(double percentage, String text) {
        MainWindow.getInstance().setLoadingProgress(percentage, text);
    }

    @Override
    public void startApplication(String[] args) {
        this.mainWindow.start(args);
    }
}
