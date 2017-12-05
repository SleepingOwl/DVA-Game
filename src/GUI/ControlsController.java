package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlsController {

    @FXML
    private Button back;


    @FXML
    private void handleBackButton(ActionEvent event) throws IOException{
        Parent root;
        Stage stage;

        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("../resources/fxml/main-menu.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
