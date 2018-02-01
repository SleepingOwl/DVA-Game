package controllers.authorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.RegistrationModel;

public class RegistrationController {

    private RegistrationModel registrationModel = new RegistrationModel();

    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField intAge;

    @FXML private Label ifSuccess;

    @FXML
    private Button back;
    @FXML
    private Button register;

    @FXML
    private void buttonHandler(ActionEvent event) {
        if (event.getSource() == register) {
            registrationModel.registerUser(txtUsername.getText(), txtPassword.getText(), txtFirstName.getText(), txtLastName.getText(), Integer.parseInt(intAge.getText()));
            ifSuccess.setText("ОК");
        }
        else {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.close();
        }
    }
}
