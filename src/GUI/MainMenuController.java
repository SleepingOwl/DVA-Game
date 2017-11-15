package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button start;
    @FXML
    private Button settings;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;

        if(event.getSource() == start) {
            stage = new Stage();
            root = new Group();
            scene = new Scene(root, 600,400);
        }
        else {
            stage = (Stage) settings.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("settings-menu.fxml"));
            scene = new Scene(root);
        }
        stage.setScene(scene);
        stage.show();
    }
}
