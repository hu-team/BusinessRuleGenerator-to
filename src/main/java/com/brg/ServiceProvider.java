package com.brg;

import com.brg.analyse.AnalyseService;
import com.brg.analyse.AnalyseServiceImpl;
import com.brg.controller.ControllerService;
import com.brg.controller.ControllerServiceImpl;
import com.brg.dao.DaoService;
import com.brg.dao.DaoServiceImpl;
import com.brg.persistence.PersistanceService;
import com.brg.persistence.PersistanceServiceImpl;

public class ServiceProvider {
    private ControllerService controllerService;
    private PersistanceService persistanceService;
    private AnalyseService analyseService;
    private DaoService daoService;

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
        persistanceService = new PersistanceService();
        analyseService = new AnalyseService();
        daoService = new DaoService();
    }


    public ControllerServiceImpl getControllerService() {
        return this.controllerService;
    }

    public PersistanceServiceImpl getPersistanceService() {
        return this.persistanceService;
    }

    public AnalyseServiceImpl getAnalyseService() {
        return this.analyseService;
    }

    public DaoServiceImpl getDaoService() {
        return this.daoService;
    }

}
