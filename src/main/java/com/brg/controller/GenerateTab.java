package com.brg.controller;

import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GenerateTab implements Initializable, TabControllerImpl {

    @FXML private TextArea outputText;
    @FXML private Button exportButton;
    @FXML private ComboBox<BusinessRule> selectRule;
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
        ServiceProvider.getInstance().getAnalyseService().getDatabaseService(); // TODO: DdlUtils implementing and verify

        // Start loading import progress from repository
        ServiceProvider.getInstance().getPersistenceService().getBusinessRuleService().reloadRules();

        // Fill in the combobox, clear the other elements.
        this.clearSelections();
        this.selectRule.getItems().addAll(FXCollections.observableArrayList(ServiceProvider.getInstance().getPersistenceService().getBusinessRuleService().getRules()));

        // Start the subview
        MainWindow.getInstance().setDisabled(false);
    }

    private void clearSelections() {
        this.selectRule.getItems().clear();
        this.outputText.setText("");
        this.outputText.setEditable(false);
    }

    public void doExport(ActionEvent actionEvent) {
        String output = "Error!";
        try {
            output = ServiceProvider.getInstance().getExportService().createExport(this.selectRule.getSelectionModel().getSelectedItem()).getOutput();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.outputText.setText(output);
    }
}
