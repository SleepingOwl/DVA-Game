package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button start;
    @FXML
    private Button controls;
    @FXML
    private Button authors;

    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;

        if(event.getSource() == start) {
            stage = (Stage) start.getScene().getWindow();;
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/logination-scene.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource() == controls){
            stage = (Stage) controls.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/control-menu.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            stage = (Stage) authors.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/authors.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
}
