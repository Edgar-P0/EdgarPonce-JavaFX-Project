package FXML_ASSIGNMENT3;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    
    int patternOrder = 0;
    Random random = new Random();
    int counter = 0;
    int turn = 1;

    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<String> pattern = new ArrayList<>();

    ArrayList<String> possibilities = new ArrayList<>(Arrays.asList("button0", "button1", "button2", "button3", "button4", "button5", "button6", "button7", "button8"));

    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Text text;

    // Start button handler
    @FXML
    void start(ActionEvent event) {
        pattern.clear();

        pattern.add(possibilities.get(random.nextInt(possibilities.size())));
        showPattern();
        System.out.println(pattern);

        counter = 0;
        turn = 1;
    }

    @FXML
        void exit(ActionEvent event) {
        System.exit(0);
    }
    
    // Save button to make game_data_*.txt
    @FXML
    private Button saveButton;

    private int saveCounter = 1;
    @FXML
    void save(ActionEvent event) {
        System.out.println("Saved Data");
        String fileName = "game_data_" + saveCounter++ + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Current level: " + (turn - 1));
            writer.newLine();
            writer.write("Pattern: " + pattern.toString());
            writer.newLine();
            writer.write("Turn: " + turn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Load button handler
    @FXML
    void load(ActionEvent event) throws IOException {
        Stage loadStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Load.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        loadStage.setScene(scene);
        loadStage.show();
    
        LoadController loadController = loader.getController();
        loadController.setParentController(this);
    }
    
    // Read saved game data and update game state
    public void loadSave(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String levelLine = reader.readLine();
            String patternLine = reader.readLine();
    
            int level = Integer.parseInt(levelLine.split(": ")[1]);
            String patternString = patternLine.split(": ")[1];
            ArrayList<String> pattern = new ArrayList<>(Arrays.asList(patternString.substring(1, patternString.length() - 1).split(", ")));
    
            this.turn = level + 1;
            this.pattern = pattern;
    
            showPattern();
            System.out.println(pattern);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialize the controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons.addAll(Arrays.asList(button0, button1, button2, button3, button4,
                button5, button6, button7, button8));
    }

    // Determines userinput for button
    @FXML
    void buttonClicked(ActionEvent event) {
        // Correct input
        if(((Control)event.getSource()).getId().equals(pattern.get(counter))){
            text.setText("Level: " + counter);
            Button button = buttons.get(getIndexOfButton(event));
            changeButtonColor(button, "-fx-base: green");
            counter++;
        } else {
        // Wrong input
            Button button = buttons.get(getIndexOfButton(event));
            changeButtonColor(button, "-fx-base: red");
            text.setText("Incorrect");
            return;
        }
        if(counter == turn){
            nextTurn();
        }
    }

    // Sets the button to select
    private void showNext(){
        Button button = buttons.get(getIndexOfButton(pattern.get(patternOrder)));
        changeButtonColor(button, "-fx-base: blue");
        patternOrder++;

        if(patternOrder == turn){
            patternOrder = 0;
        }
    }

    // Time for game to begin
    private void changeButtonColor(Button button, String color){
        button.setStyle(color);
        PauseTransition pause = new PauseTransition(
                Duration.seconds(.6));
        pause.setOnFinished(e -> {
            button.setStyle(null);
        });
        pause.play();
    }

    // Duration time for button to light up blue
    private void showPattern(){
        PauseTransition pause = new PauseTransition(
                Duration.seconds(1));
        pause.setOnFinished(e -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                showNext();
            }));
            timeline.setCycleCount(pattern.size());
            timeline.play();
        });
        pause.play();
    }

    private int getIndexOfButton(ActionEvent event){
        String buttonId = ((Control)event.getSource()).getId();
        return Integer.parseInt(buttonId.substring(buttonId.length() -1));
    }

    private int getIndexOfButton(String button){
        return Integer.parseInt(button.substring(button.length() -1));
    }

    // Gets pattern and adds turns
    private void nextTurn(){
        counter = 0;
        turn++;

        pattern.add(possibilities.get(random.nextInt(possibilities.size())));
        showPattern();
        System.out.println(pattern);
    }
}