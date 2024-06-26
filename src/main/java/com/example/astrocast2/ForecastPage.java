package com.example.astrocast2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class ForecastPage {
    public void display(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        // Retrieve the screen size
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();
        // Creates the screen with the retrieved proportions
        Scene scene = new Scene(fxmlLoader.load(), screenWidth*0.8, screenHeight*0.8);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
