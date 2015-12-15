package com.brg.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GenerateTab implements Initializable, TabControllerImpl {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void updateContents() {
        System.out.println("Update tab!");
    }
}
