package controllers.authorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private void buttonHandler(ActionEvent event) throws IOException{
        Stage stage;
        Scene scene;
        if(event.getSource() == login){
            stage = (Stage) login.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/main-menu.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource() == register){
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/authorization/registration.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
