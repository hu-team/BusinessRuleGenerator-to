package com.brg.controller;

import com.aquafx_project.AquaFx;
import com.aquafx_project.controls.skin.styles.ButtonType;
import com.aquafx_project.controls.skin.styles.ControlSizeVariant;
import com.aquafx_project.controls.skin.styles.MacOSDefaultIcons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Splash Window
 */
public class SplashWindow implements Initializable {
    @FXML private Button splashClose;
    @FXML private ImageView splashImage;
    @FXML private Label splashLabel;
    @FXML private ProgressBar splashProgress;

    private boolean started;

    private Stage stage;
    private Parent root;
    private boolean willStart;

    public SplashWindow() {
        this.started = false;
        this.willStart = false;
    }

    public void start() {
        if (this.stage == null) {
            this.willStart = true;
            return;
        }

        this.stage.show();
        this.started = true;
    }

    public void stop() {
        if (this.stage != null) {
            this.stage.hide();
            this.started = false;
        }
    }

    public boolean isStarted() {
        return started;
    }

    public void update(int percentage, String text) {
        if (percentage >= 0 && percentage <= 100) {
            this.splashProgress.setProgress(percentage / 100);
        } else {
            this.splashProgress.setProgress(-1.0f);
        }

        if (text != null) {
            this.splashLabel.setText(text);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize fxml
        this.splashProgress.setProgress(-1.0f);
        this.splashLabel.setText("Loading...");

        InputStream imageStream = getClass().getClassLoader().getResourceAsStream("splash.png");
        if (imageStream != null) {
            this.splashImage.setImage(new Image(imageStream));
        }

        AquaFx.createLabelStyler().style(this.splashLabel);
        AquaFx.createProgressBarStyler().setSizeVariant(ControlSizeVariant.REGULAR).style(this.splashProgress);
        AquaFx.createButtonStyler().setType(ButtonType.CENTER_PILL).setSizeVariant(ControlSizeVariant.MINI).style(this.splashClose);
    }

    public void prepare() {
        // Prepare the stage.
        try {
            URL fxml = getClass().getClassLoader().getResource("splashWindow.fxml");
            assert fxml != null;

            root = FXMLLoader.load(fxml);

            stage = new Stage();
            AquaFx.styleStage(this.stage, StageStyle.UNDECORATED);

            stage.setTitle("Loading...");
            stage.setScene(new Scene(root, 502, 302));
            stage.setAlwaysOnTop(true);
            stage.setResizable(false);

            if (this.willStart) {
                this.start();
                this.willStart = false;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void clickSplashClose(ActionEvent actionEvent) {
        // Close forced
        System.exit(1);
    }
}
