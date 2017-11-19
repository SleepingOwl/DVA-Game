package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    @FXML
    private Button back;
    @FXML
    private Button apply;
    @FXML
    private TextField size;
    @FXML
    private TextField speed;
    @FXML
    private ChoiceBox<Main.DIRECTION> choiceDirection;

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException{
        Parent root;
        Stage stage;

        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void applyChange(ActionEvent event) {

    }
}
