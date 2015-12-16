package com.brg.controller;

import com.brg.ServiceProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GenerateTab implements Initializable, TabControllerImpl {

    @FXML private AnchorPane generateTabAnchor;

    private MainWindow rootController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateContents();
    }

    @Override
    public void updateContents() {
        // Set all content disabled
        MainWindow.getInstance().setDisabled(true);

        // Start loading target database structure
        ServiceProvider.getInstance().getAnalyseService().getDatabaseService();
        // TODO: Werkt niet in prototype, database structuur onderzoeken.

        // Start loading import progress from repository
        ServiceProvider.getInstance().getPersistanceService().getBusinessRuleService().reloadRules();

        // Start the subview
        MainWindow.getInstance().setDisabled(false);
    }
}
