package com.brg.controller;

import com.brg.common.AbstractFacadeService;

public class ControllerService extends AbstractFacadeService implements ControllerServiceImpl {

    private MainWindow mainWindow;

    public ControllerService() {
        this.mainWindow = new MainWindow();
    }

    @Override
    public MainWindow getMainWindow() {
        return this.mainWindow;
    }
}
