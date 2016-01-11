package com.brg.controller;

import com.brg.domain.DatabaseType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SettingsTab implements Initializable, TabControllerImpl {

    @FXML private ComboBox<DatabaseType> exportOptions;
    private ArrayList<DatabaseType> arrDatabaseType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateContents();
    }

    @Override
    public void updateContents() {
        this.exportOptions.getItems().addAll(FXCollections.observableArrayList(getGetDatabaseType()));
    }

    public ArrayList<DatabaseType> getGetDatabaseType() {
        arrDatabaseType = new ArrayList<DatabaseType>();

        for (DatabaseType dbt : DatabaseType.values()) {
            arrDatabaseType.add(dbt);
        }

        return arrDatabaseType;
    }

    public void loadSettings() {

    }

    public void saveSettings() {
        
    }
}
