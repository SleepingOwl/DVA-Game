package controllers.statistic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PreviewController {

    @FXML
    private Button start;
    @FXML
    private Button back;

    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;

        if (event.getSource() == start) {

        } else {
            stage = (Stage) back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/main-menu.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
