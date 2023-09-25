package FXML_ASSIGNMENT3;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void signIn() throws IOException {
        // Get the text from the usernameField and passwordField
        String username = usernameField.getText();  
        String password = passwordField.getText();

        // Validate if the username and password are correct
        if (username.equals("admin") && password.equals("password123")) {
            // If the credentials are correct, show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sign In");
            alert.setHeaderText(null);
            alert.setContentText("Sign in successful!");
            alert.showAndWait();

            // Load the PatternGame.fxml file and display it in a new window if credentials are correct
            Parent root = FXMLLoader.load(getClass().getResource("PatternGame.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            // If the credentials are incorrect, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign In");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }
}
