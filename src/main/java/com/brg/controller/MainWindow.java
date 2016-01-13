package com.brg.controller;

import com.aquafx_project.AquaFx;
import com.brg.BusinessRuleGenerator;
import com.brg.ServiceProvider;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindow extends Application implements Initializable {
    private static MainWindow instance;

    private SplashWindow splashWindow;

    @FXML private TabPane tabPane;

    private Stage stage;

    /**
     * Declare Tab names with the FXML files.
     */
    private String[][] tabNames = {
            {"Define rules", "defineTab.fxml"},
            {"Generate rules", "generateTab.fxml"},
            {"Settings", "settingsTab.fxml"}
    };

    /**
     * Tab Controller holder.
     */
    private HashMap<String, TabControllerImpl> tabControllers = new HashMap<String, TabControllerImpl>();

    private Parent content;


    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        this.stage = primaryStage;

        try {
            this.content = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        } catch(Exception e ) {
            e.printStackTrace();
            return;
        }

        primaryStage.setResizable(true);
        primaryStage.setTitle("Business Rule Generator (Version: " + BusinessRuleGenerator.VERSION + ", Build: " + BusinessRuleGenerator.BUILD + ")");


        // Make main scene
        Scene scene = new Scene(this.content);

        primaryStage.setScene(scene);
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

    /**
     * Start the main window application.
     *
     * @param args
     */
    public void start(String[] args) {
        launch(args);
    }

    /**
     * Initialize method - Parse our defined tabs and add to the tab bar.
     *
     * @param location Location
     * @param resources Resources (Bundle)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Define tabs
        for (String[] tabName : this.tabNames) {
            this.tabPane.getTabs().add(new Tab(tabName[0]));
        }

        // Set actions
        this.tabPane.getSelectionModel().clearSelection();

        // Add Tab ChangeListener
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                if (newValue.getContent() == null) {
                    try {
                        // Search for filename
                        String fileName = null;
                        for (String[] tabName: tabNames) {
                            if (newValue.getText().equals(tabName[0])) {
                                fileName = tabName[1];
                            }
                        }

                        // Loading content on demand
                        FXMLLoader loader = new FXMLLoader();
                        Parent root = loader.load(this.getClass().getResource("/tabs/" + fileName).openStream());
                        newValue.setContent(root);

                        // Store the controller
                        tabControllers.put(newValue.getText(), loader.getController());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    // Content is already loaded. Update it if necessary.
                    Parent root = (Parent) newValue.getContent();

                    // Send update event
                    TabControllerImpl controller = tabControllers.get(newValue.getText());
                    if (controller != null) {
                        controller.updateContents();
                    }
                }
            }
        });

        // Load first tab
        tabPane.getSelectionModel().selectFirst();

        // Start second start sequence
        ServiceProvider.getInstance().startSecondWave();
    }


    /**
     * Set disabled state on golbal window
     * @param disabled boolean Disable?
     */
    public void setDisabled(boolean disabled) {
        if (this.tabPane != null) {
            this.tabPane.setDisable(disabled);
        }
        if (this.content != null) {
            this.content.setDisable(disabled);
        }
    }


    /**
     * Get current instance of MainWindow
     * @return MainWindow instance
     */
    public static MainWindow getInstance() {
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    /**
     * Get splash window instance
     * @return splash window controller instance
     */
    public SplashWindow getSplashWindow() {
        try {
            if (this.splashWindow == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("splashWindow.fxml"));
                Parent splashRoot = loader.load();

                this.splashWindow = loader.getController();
                this.splashWindow.prepare();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return this.splashWindow;
    }
}
