package com.brg.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DefineTab implements Initializable, TabControllerImpl {

    @FXML private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateContents();
        this.webView.getEngine().load("https://ondora02.hu.nl:8080/ords/f?p=1567:LOGIN_DESKTOP:15608328208288:::::");

        this.webView.getEngine().setOnAlert(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Web Alert");
            alert.setContentText(event.getData());

            alert.showAndWait();
        });

        this.webView.getEngine().setConfirmHandler((param) -> {
            // Ask
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Web Confirm");
            alert.setContentText(param);

            Optional<ButtonType> result = alert.showAndWait();
            return result.get() != ButtonType.CANCEL;
        });

        this.webView.getEngine().setOnError(event -> {
            System.err.println(event.getMessage());
        });
    }

    @Override
    public void updateContents() {
        this.webView.autosize();
    }
}
