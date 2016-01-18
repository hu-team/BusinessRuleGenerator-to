package com.brg.analyse;


import com.brg.common.AbstractFacadeService;

/**
 * Analyse Public Facade Service
 */
public class AnalyseServiceImpl extends AbstractFacadeService implements AnalyseService {
    private DatabaseService databaseService = new DatabaseService();

    @Override
    public DatabaseService getDatabaseService() {
        return this.databaseService;
    }

    @Override
    public void willExitApplication() {
        this.getDatabaseService().willExitApplication();
    }
}
