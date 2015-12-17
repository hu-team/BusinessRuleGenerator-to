package com.brg.persistence;


import com.brg.common.AbstractFacadeService;
import com.brg.common.Config;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersistanceService extends AbstractFacadeService implements PersistanceServiceImpl {

    private Connection connection;

    private BusinessRuleService businessRuleService = new BusinessRuleService();
    private RuleValueBundleService ruleValueBundleService = new RuleValueBundleService();

    public PersistanceService() {
        this.makeConnection();
    }

    private void makeConnection() {
        // TODO: Move this to the connection holder(s). This is only for prototype.
        // TODO: Refactor
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException clfe) {
            clfe.printStackTrace();
            return;
        }

        Properties p;

        try {
            p = Config.getInstance().getConfig();
        }catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }

        String connectString = "jdbc:oracle:thin:@//" + p.getProperty("repository_host");
        connectString += ":" + p.getProperty("repository_port");
        connectString += "/" + p.getProperty("repository_service");

        try{
            connection = DriverManager.getConnection(connectString, p.getProperty("repository_username"), p.getProperty("repository_password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BusinessRuleService getBusinessRuleService() {
        return this.businessRuleService;
    }

    @Override
    public RuleValueBundleService getRuleValueBundleService() {
        return this.ruleValueBundleService;
    }

    @Override
    public Connection getConnection() {
        try {
            if (this.connection.isClosed()) {
                this.makeConnection();
            }
        }catch (SQLException se) {
            this.makeConnection();
        }
        return this.connection;
    }

}
