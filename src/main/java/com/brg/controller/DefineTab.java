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
        this.webView.getEngine().load("http://www.google.com/");
        this.webView.autosize();
    }

    @Override
    public void updateContents() {
        this.webView.getEngine().load("http://www.google.com/");
        this.webView.autosize();
    }
}
