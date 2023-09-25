package FXML_ASSIGNMENT3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoadController extends Controller {
    // Declare a parentController object
    private Controller parentController;

    // Declare a loadButton button object
    @FXML
    private Button loadButton;

    // Declare a fileNameTextField text field object
    @FXML
    private TextField fileNameTextField;

    @FXML
    void load(ActionEvent event) {
        // Get the text from the fileNameTextField text field
        String fileName = fileNameTextField.getText();
        try {
            // Creates new fileReader object to read
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            reader.close();
            
            // Call the loadSave method
            parentController.loadSave(fileName);
        } catch (IOException e) {
            System.out.println("Incorrect file input");
        }
    }
    
    // Setter method to set the parentController object
    public void setParentController(Controller parentController) {
        this.parentController = parentController;
    }
    
}
