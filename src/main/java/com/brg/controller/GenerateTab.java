package com.brg.controller;

import com.brg.ServiceProvider;
import com.brg.common.ExceptionAlert;
import com.brg.domain.BusinessRule;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GenerateTab implements Initializable, TabControllerImpl {

    @FXML private TextArea outputText;

    @FXML private Button exportButton;
    @FXML private Button validateButton;
    @FXML private Button applyTarget;

    @FXML private ListView<BusinessRule> selectRule;

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

        // Start loading import progress from repository
        ServiceProvider.getInstance().getPersistenceService().reloadRules();

        // Fill in the combobox, clear the other elements.
        this.clearSelections();

        this.selectRule.getItems().addAll(FXCollections.observableArrayList(FXCollections.observableArrayList(ServiceProvider.getInstance().getPersistenceService().getAllRules())));

        // Start the subview
        MainWindow.getInstance().setDisabled(false);
    }

    private void clearSelections() {
        this.selectRule.getItems().clear();
        this.outputText.setText("");
        this.outputText.setEditable(false);
    }

    @FXML
    private void clickExport(MouseEvent click) throws Exception {
        if(this.selectRule.getSelectionModel().getSelectedItem() == null){
            return;
        }

        if(click.getClickCount() == 2){
            String output = "";

            try {
                output = ServiceProvider.getInstance().getExportService().createExport(this.selectRule.getSelectionModel().getSelectedItem()).getOutput();
            }catch (ClassNotFoundException e){
                ExceptionAlert alert = new ExceptionAlert(e);
                alert.showAndWait();
            }

            this.outputText.setText(output);
        }

    }

    @FXML
    private void doExport(ActionEvent actionEvent) throws Exception {
        String output = "Error!";

        if(this.selectRule.getSelectionModel().getSelectedItem() == null ){
            return;
        }

        try {
            output = ServiceProvider.getInstance().getExportService().createExport(this.selectRule.getSelectionModel().getSelectedItem()).getOutput();
        } catch (ClassNotFoundException e) {
            ExceptionAlert alert = new ExceptionAlert(e);
            alert.showAndWait();
        }


        this.outputText.setText(output);
    }

    @FXML
    private void doApply() throws Exception {
        if (this.selectRule.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        ServiceProvider.getInstance().getExportService().applyExport(this.selectRule.getSelectionModel().getSelectedItem());

        // Done
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rule has been applied");
        alert.setHeaderText("Rule has been applied");
        alert.setContentText("The rule trigger code has been applied to the target database!");
        alert.showAndWait();
    }

    @FXML
    private void doValidate(ActionEvent actionEvent) {
        if(this.selectRule.getSelectionModel().getSelectedItem() == null ){
            return;
        }

        boolean selectedbusinnes = this.selectRule.getSelectionModel().getSelectedItem().validateRule();

        if(selectedbusinnes) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validate Rule");
            alert.setHeaderText("Code is valid");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validate Rule");
            alert.setHeaderText("Code is invalid");
            alert.showAndWait();
        }

    }
}
