package com.brg.controller;

import com.brg.BusinessRuleGenerator;
import com.brg.ServiceProvider;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindow extends Application implements Initializable {
    private static MainWindow instance;

    @FXML private TabPane tabPane;
    @FXML private ProgressBar loadingProgress;
    @FXML private Label loadingText;

    @FXML private MenuItem exitMenuItem;
    @FXML private MenuItem aboutMenuItem;

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

    private boolean hide;


    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        hide = false;
        this.stage = primaryStage;

        FXMLLoader loader = new FXMLLoader();

        try {
            this.content = loader.load(getClass().getResourceAsStream("/mainWindow.fxml"));
            instance = loader.getController();
        } catch(Exception e ) {
            e.printStackTrace();
            return;
        }

        primaryStage.setResizable(false);
        primaryStage.setTitle("Business Rule Generator (Version: " + BusinessRuleGenerator.VERSION + ", Build: " + BusinessRuleGenerator.BUILD + ")");

        // Make main scene
        Scene scene = new Scene(this.content);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);

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
                return;
            }

            // Close all threads.
            ServiceProvider.getInstance().willExitApplication();

            // Force exit for all threads.
            System.exit(0);
        });

        // Start second start sequence
        ServiceProvider.getInstance().startSecondWave();
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
    }

    @FXML
    private void handleMenuItemEvent(ActionEvent event) throws IOException {
        if(event.getSource() == exitMenuItem){
            System.out.println("EXIT SYSTEM");
        }else if(event.getSource() == aboutMenuItem){

            Alert about = new Alert(Alert.AlertType.INFORMATION);
            about.setTitle("About");
            about.setHeaderText("License");
            String aboutContent = "The MIT License (MIT)\n" +
                    "\n" +
                    "Copyright (c) 2015-2016 Tom & HU Team 5\n" +
                    "\n" +
                    "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                    "of this software and associated documentation files (the \"Software\"), to deal\n" +
                    "in the Software without restriction, including without limitation the rights\n" +
                    "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                    "copies of the Software, and to permit persons to whom the Software is\n" +
                    "furnished to do so, subject to the following conditions:\n" +
                    "\n" +
                    "The above copyright notice and this permission notice shall be included in all\n" +
                    "copies or substantial portions of the Software.\n" +
                    "\n" +
                    "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                    "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                    "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                    "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                    "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                    "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
                    "SOFTWARE.\n";
            about.setContentText(aboutContent);
            about.show();

        }
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

    public void setHide(boolean hide) {
        this.hide = hide;
        if (this.stage != null) {
            if (hide) {
                this.stage.hide();
            }else{
                this.stage.show();
            }
        }
    }

    public void setLoadingProgress(double percentage, String text) {
        if (text != null) {
            this.loadingText.setText(text);
        }
        this.loadingProgress.setProgress(percentage);
    }
}
