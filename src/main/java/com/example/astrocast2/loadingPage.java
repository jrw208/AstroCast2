package com.example.astrocast2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class loadingPage extends Application{
    final private double screenWidth;
    final private double screenHeight;

    public loadingPage(double x, double y){
        screenWidth = x;
        screenHeight = y;
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a label for the loading message
        Label loadingLabel = new Label("Loading...");

        // Create a StackPane for the loading page content
        StackPane loadingPane = new StackPane(loadingLabel);
        Scene loadingScene = new Scene(loadingPane, screenWidth, screenHeight);

        // Set the loading scene
        primaryStage.setScene(loadingScene);
        primaryStage.setTitle("Loading");
    }
}
