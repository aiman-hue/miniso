package ecommerce;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RegisterScreen {
    private VBox root;

    public RegisterScreen(Stage stage) {
        VBox formBox = new VBox(10);
        formBox.getStyleClass().add("auth-box");

        Label titleLabel = new Label("Register on MINISO");
        titleLabel.getStyleClass().add("screen-title");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleRadio, femaleRadio);
        genderBox.setAlignment(Pos.CENTER_LEFT);

        PasswordField userField = new PasswordField();
        userField.setPromptText("register as user");

        Button registerBtn = new Button("Register");
        Button backBtn = new Button("Back to Login");

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("message-label");

        registerBtn.setOnAction((ActionEvent e) -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
            String gender = selectedGender != null ? selectedGender.getText() : "";
            String role = userField.getText().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender.isEmpty()) {
                messageLabel.setText("Please fill all fields.");
            } else if (!password.equals(confirmPassword)) {
                messageLabel.setText("Passwords do not match.");
            } else if (UserDataManager.userExists(username)) {
                messageLabel.setText("Username already exists.");
            } else {
                UserDataManager.saveUser(username, password, email, gender , role);
                LoginScreen login = new LoginScreen(stage);
                Scene scene = new Scene(login.getRoot(), 1000, 600);
                scene.getStylesheets().add(getClass().getResource("/resources/styles/style.css").toExternalForm());
                stage.setScene(scene);
            }
        });

        backBtn.setOnAction(e -> {
            LoginScreen login = new LoginScreen(stage);
            Scene scene = new Scene(login.getRoot(), 1000, 600);
            scene.getStylesheets().add(getClass().getResource("/resources/styles/style.css").toExternalForm());
            stage.setScene(scene); stage.setScene(scene);
        
        stage.setMaximized(true);            
        
        stage.show();
        });

        formBox.getChildren().addAll(
                titleLabel,
                usernameField,
                emailField,
                passwordField,
                confirmPasswordField,
                new Label("Gender:"),
                genderBox,
                registerBtn,
                backBtn,
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
