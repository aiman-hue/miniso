/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ecommerce extends Application {
    
   @Override
    public void start(Stage stage) {
        LoginScreen loginScreen = new LoginScreen(stage);
        Scene scene = new Scene(loginScreen.getRoot(), 1000, 600);
        
        URL resource = getClass().getResource("/resources/styles/style.css");
if (resource == null) {
    System.out.println("Resource not found!");
} else {
    scene.getStylesheets().add(resource.toExternalForm());
} stage.setScene(scene);

        // Maximize the window (or use full screen if desired)
        stage.setMaximized(true);                // Normal maximized mode
        // stage.setFullScreen(true);           // Optional: uncomment for full-screen lock
        // stage.setFullScreenExitHint("");     // Optional: remove "press ESC" message

        // Show the main window
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
