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
    private void switchScene(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;

        if(event.getSource() == start) {
            stage = new Stage();
            Group root = new Group();
            scene = new Scene(root, 600,400);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();

            RingModel ringModel = new RingModel(stage.getWidth()/2, stage.getHeight()/2);
            ringModel.resize(0.15);

            VectorRingModel vectorRingModel = new VectorRingModel(stage.getWidth()/2 + 150, stage.getHeight()/2);
            vectorRingModel.resize(0.5);
            root.getChildren().addAll(ringModel.getMovingPane(), vectorRingModel.getMovingPane());
        }
        else {
            stage = (Stage) settings.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("settings-menu.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
}
