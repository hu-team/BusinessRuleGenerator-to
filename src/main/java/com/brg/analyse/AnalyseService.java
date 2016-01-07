package com.brg.analyse;


import com.brg.common.AbstractFacadeService;

/**
 * Analyse Public Facade Service
 */
public class AnalyseService extends AbstractFacadeService implements AnalyseServiceImpl {

    private DatabaseService databaseService = new DatabaseService();

    @Override
    public DatabaseService getDatabaseService() {
        return this.databaseService;
    }
}
