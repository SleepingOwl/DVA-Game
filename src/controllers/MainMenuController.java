package controllers;

import controllers.animation.AnimationSceneController;
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
    private Button changeUser;
    @FXML
    private Button start;
    @FXML
    private Button controls;
    @FXML
    private Button preview;
//    @FXML
//    private Button authors;
    @FXML
    public AnimationSceneController animationSceneController;

    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;

        if(event.getSource() == start) {
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/animation/animation-scene.fxml"));
            Parent root = loader.load();
            scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setFullScreen(true);
            animationSceneController = loader.getController();
            animationSceneController.setStage(stage);
            stage.show();
            scene.getRoot().requestFocus();

        }
        else if(event.getSource() == controls){
            stage = (Stage) controls.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/control-menu.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource() == preview){
            stage = (Stage) start.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/statistic/preview.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource() == changeUser){
            stage = (Stage) start.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/statistic/login.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
//        else {
//            stage = (Stage) authors.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/authors.fxml"));
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }

    }
}
