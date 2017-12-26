package controllers.authorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private Button back;

    @FXML
    private Button register;

    @FXML
    private void buttonHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
}
