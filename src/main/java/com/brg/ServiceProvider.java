package com.brg;

import com.brg.analyse.AnalyseServiceImpl;
import com.brg.controller.ControllerServiceImpl;
import com.brg.dao.DaoServiceImpl;
import com.brg.generate.ExportServiceImpl;
import com.brg.persistence.PersistenceServiceImpl;

public class ServiceProvider {
    private ControllerServiceImpl controllerService;
    private PersistenceServiceImpl persistenceService;
    private AnalyseServiceImpl analyseService;
    private DaoServiceImpl daoService;
    private ExportServiceImpl exportService;

    private static ServiceProvider instance;


    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    private ServiceProvider() {
        // Make instances of all services (facades)
        controllerService = new ControllerServiceImpl();
        persistenceService = new PersistenceServiceImpl();
        analyseService = new AnalyseServiceImpl();
        daoService = new DaoServiceImpl();
        exportService = new ExportServiceImpl();
    }


    public ControllerServiceImpl getControllerService() {
        return this.controllerService;
    }

    public PersistenceServiceImpl getPersistenceService() {
        return this.persistenceService;
    }

    public AnalyseServiceImpl getAnalyseService() {
        return this.analyseService;
    }

    public DaoServiceImpl getDaoService() {
        return this.daoService;
    }

    public ExportServiceImpl getExportService() { return this.exportService; }


    public void startFirstWave(String[] args) {
        this.controllerService.startApplication(args);
    }

    public void startSecondWave() {
        this.getAnalyseService().startIndexingTargetDatabase();
        this.controllerService.setLoadingProgress(-1.0, "Loading target database structure...");
    }

    public void willExitApplication() {

    }
}
