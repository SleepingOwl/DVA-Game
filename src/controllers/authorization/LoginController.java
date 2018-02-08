package controllers.authorization;

import controllers.MainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private LoginModel loginModel = new LoginModel();

    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private Label txtIncorrect;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected())
            txtIncorrect.setText("Connected");
        else
            txtIncorrect.setText("Not Connected");
    }

    @FXML
    private void buttonHandler(ActionEvent event) throws IOException{
        Stage stage;
        Scene scene;
        if(event.getSource() == login){
            try {
                if (loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {
                    MainMenuController.sessionId = loginModel.getUserId(txtUsername.getText());
                    stage = (Stage) login.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/main-menu.fxml"));
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    txtIncorrect.setText("Неверное имя пользователя или пароль");
                }
            } catch (SQLException e) {
                txtIncorrect.setText("Неверное имя пользователя или пароль");
                e.printStackTrace();
            }
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
