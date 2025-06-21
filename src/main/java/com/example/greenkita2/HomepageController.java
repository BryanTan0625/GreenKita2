package com.example.greenkita2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HomepageController {

        @FXML
        private Button activismbutton;

        @FXML
        private ImageView coinbutton;

        @FXML
        private Button educationbutton;

        @FXML
        private ImageView iconbutton;

        @FXML
        private Button recyclebutton;

        @FXML
        private Button transportbutton;



        public HomepageController(){}

        @FXML
        public void initialize(){
            this.transportbutton.setOnAction((event) -> this.navigateTo("Transportpage.fxml"));

            this.recyclebutton.setOnAction((event) -> this.navigateTo("Recyclingpage.fxml"));
            this.educationbutton.setOnAction((event) -> this.navigateTo("Educationpage.fxml"));

        }
    private void navigateTo(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene newScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) transportbutton.getScene().getWindow();
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
