package com.brg;

import com.brg.controller.ControllerService;
import com.brg.controller.ControllerServiceImpl;

public class ServiceProvider {
    private ControllerService controllerService;

    private static ServiceProvider instance;


    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    private ServiceProvider() {
        // Make instances of all services (facades)
        controllerService = new ControllerService();
    }


    public ControllerServiceImpl getControllerService() {
        return this.controllerService;
    }
}
