package com.brg.controller;

import com.brg.common.Config;
import com.brg.domain.DatabaseType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingsTab implements Initializable, TabControllerImpl {

    @FXML private ComboBox<DatabaseType> exportOptions;
    @FXML private TextField targetHost, targetUsername, targetPassword, targetService, targetPort;
    private ArrayList<DatabaseType> arrDatabaseType;
    private Properties properties;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateContents();
    }

    @Override
    public void updateContents() {
        loadSettings();
        String selected = properties.getProperty("target_type");
        exportOptions.getItems().addAll(FXCollections.observableArrayList(getGetDatabaseType()));
        exportOptions.setValue(selectedDatabaseType(selected));

    }

    public ArrayList<DatabaseType> getGetDatabaseType() {
        arrDatabaseType = new ArrayList<DatabaseType>();

        for (DatabaseType dbt : DatabaseType.values()) {
            arrDatabaseType.add(dbt);
        }

        return arrDatabaseType;
    }

    private void loadSettings() {
        try {
            properties = Config.getInstance().getConfig();
            setSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DatabaseType selectedDatabaseType(String dbt) {
        switch (dbt) {
            case "ORACLE": return DatabaseType.ORACLE;
            case "MYSQL": return DatabaseType.MYSQL;
            default: return DatabaseType.ORACLE;
        }
    }

    private void setSettings() {
        targetHost.setText(properties.getProperty("target_host"));
        targetUsername.setText(properties.getProperty("target_username"));
        targetPassword.setText(properties.getProperty("target_password"));
        targetService.setText(properties.getProperty("target_service"));
        targetPort.setText(properties.getProperty("target_port"));
    }

    public void saveSettings() {

    }
}
