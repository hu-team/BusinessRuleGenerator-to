package com.brg;

import com.brg.analyse.AnalyseService;
import com.brg.analyse.AnalyseServiceImpl;
import com.brg.controller.ControllerService;
import com.brg.controller.ControllerServiceImpl;
import com.brg.dao.DaoService;
import com.brg.dao.DaoServiceImpl;
import com.brg.generate.ExportService;
import com.brg.generate.ExportServiceImpl;
import com.brg.persistence.PersistenceService;
import com.brg.persistence.PersistenceServiceImpl;

public class ServiceProvider {
    private ControllerService controllerService;
    private PersistenceService persistanceService;
    private AnalyseService analyseService;
    private DaoService daoService;
    private ExportService exportService;

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
        persistanceService = new PersistenceService();
        analyseService = new AnalyseService();
        daoService = new DaoService();
        exportService = new ExportService();
    }


    public ControllerServiceImpl getControllerService() {
        return this.controllerService;
    }

    public PersistenceServiceImpl getPersistenceService() {
        return this.persistanceService;
    }

    public AnalyseServiceImpl getAnalyseService() {
        return this.analyseService;
    }

    public DaoServiceImpl getDaoService() {
        return this.daoService;
    }

    public ExportServiceImpl getExportService() { return this.exportService; }

    public void startSecondWave() {
        this.analyseService.getDatabaseService().startIndexingTargetDatabase();
        this.controllerService.setLoadingProgress(-1.0, "Loading target database structure...");
    }

    public void willExitApplication() {

    }
}
