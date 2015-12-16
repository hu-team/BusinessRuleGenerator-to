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
    }

    @Override
    public void updateContents() {
        this.webView.getEngine().load("https://apex.oracle.com/pls/apex/f?p=88436:1:117546319450435:::::");
        this.webView.autosize();
    }
}
