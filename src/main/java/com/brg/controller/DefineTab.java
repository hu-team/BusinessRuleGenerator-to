package com.brg.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class DefineTab implements Initializable, TabControllerImpl {

    @FXML private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateContents();
        this.webView.getEngine().load("https://ondora02.hu.nl:8080/ords/f?p=1567:LOGIN_DESKTOP:15608328208288:::::");
    }

    @Override
    public void updateContents() {
        this.webView.autosize();
    }
}
