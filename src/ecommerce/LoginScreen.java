/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginScreen {
    private VBox root;

    public LoginScreen(Stage stage) {
        VBox formBox = new VBox(10);
        formBox.getStyleClass().add("auth-box");

        Label titleLabel = new Label("MINISO APP");
        titleLabel.getStyleClass().add("screen-title");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("Register");

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("message-label");
        
        loginBtn.setOnAction(e -> {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
        messageLabel.setText("Please enter both fields.");
        } else {
       try {
    boolean isValid = UserDataManager.validateUser(username, password);
    if (isValid) {
        if (username.equals("admin") && password.equals("admin123")) {
            AdminDashboard adminDashboard = new AdminDashboard(stage, username);
            Scene scene = new Scene(adminDashboard.getRoot(), 1000, 600);
            scene.getStylesheets().add(getClass().getResource("/resources/styles/style.css").toExternalForm());
            stage.setScene(scene);
         // Maximize the window (or use full screen if desired)
        stage.setMaximized(true);                // Normal maximized mode
        // stage.setFullScreen(true);           // Optional: uncomment for full-screen lock
        // stage.setFullScreenExitHint("");     // Optional: remove "press ESC" message

        // Show the main window
        stage.show();
        } else {
            UserDashboard userDashboard = new UserDashboard(stage, username);
            Scene scene = new Scene(userDashboard.getRoot(), 1000, 600);
            scene.getStylesheets().add(getClass().getResource("/resources/styles/style.css").toExternalForm());
            stage.setScene(scene);
            stage.setMaximized(true);                // Normal maximized mode
        // stage.setFullScreen(true);           // Optional: uncomment for full-screen lock
        // stage.setFullScreenExitHint("");     // Optional: remove "press ESC" message

        // Show the main window
        stage.show();}
    }
    // Else: do nothing silently on login failure
} catch (Exception ex) {
    ex.printStackTrace();
    // Silently ignore or log error without alert
}


    }
});
 registerBtn.setOnAction(e -> {
    RegisterScreen registerScreen = new RegisterScreen(stage); // Pass current stage
    Scene scene = new Scene(registerScreen.getRoot(), 1000, 600);
    scene.getStylesheets().add(getClass().getResource("/resources/styles/style.css").toExternalForm());
    stage.setScene(scene);
});
        formBox.getChildren().addAll(
                titleLabel,
                usernameField,
                passwordField,
                loginBtn,
                registerBtn,
                messageLabel
        );
        root = new VBox(formBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setPrefSize(1000, 600);
        root.getStyleClass().add("root");
    }
    public VBox getRoot() {
        return root;
    }
}

