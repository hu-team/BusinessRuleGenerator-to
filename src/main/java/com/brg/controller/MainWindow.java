package com.brg.controller;

import com.brg.BusinessRuleGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        } catch(Exception e ) {
            e.printStackTrace();
            return;
        }

        primaryStage.setResizable(false);
        primaryStage.setTitle("Business Rule Generator (v" + BusinessRuleGenerator.VERSION + " " + BusinessRuleGenerator.BUILD + ")");

        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            // Don't allow closing it when not in other stage.

            // Ask for exit
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit application");
            alert.setContentText("Are you sure to exit the application?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    public void start(String[] args) {
        launch(args);
    }
}
