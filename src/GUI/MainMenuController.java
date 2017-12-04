package GUI;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button start;
    @FXML
    private Button settings;

    private static double speedMultiply = 1;
    private static double sizeMultiply = 1;

    private static double speedUp() {
        speedMultiply = speedMultiply + 0.1;
        return speedMultiply;
    }

    private static double speedDown() {
        if (speedMultiply <= 0.1)
            return 0.1;
        else {
            speedMultiply = speedMultiply - 0.1;
            return speedMultiply;
        }
    }

    private static double sizeUp() {
        sizeMultiply = sizeMultiply + 0.1;
        return sizeMultiply;
    }

    private static double sizeDown() {
        if (sizeMultiply <= 0.1)
            return 0.1;
        else {
            sizeMultiply = sizeMultiply - 0.1;
            return sizeMultiply;
        }
    }

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
//            RingModel ringModel = new RingModel(stage.getWidth()/2, stage.getHeight()/2);
//            ringModel.resize(0.15);

            VectorRingModel vectorRingModel = new VectorRingModel();
            vectorRingModel.resize(0.5);
            root.getChildren().addAll( vectorRingModel.getMovingPane() );

            AnimationModel animation = new AnimationModel(vectorRingModel.getMovingPane());
            animation.setLinearPath(50, stage.getHeight()/2,stage.getWidth()-100, stage.getHeight()/2);
            //animation.changeSpeed(3);
            animation.play();

            Timeline flickerRing = new Timeline();

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()){
                        // up-down
                        case U:
                            animation.setLinearPath(stage.getWidth()/2, 50,stage.getWidth()/2, stage.getHeight()-100);
                            break;
                        // left-right
                        case H:
                            animation.setLinearPath(stage.getWidth() - 100,  stage.getHeight()/2,50, stage.getHeight()/2);
                            break;
                        // down-up
                        case J:
                            animation.setLinearPath(stage.getWidth()/2, stage.getHeight()-100,stage.getWidth()/2, 50);
                            break;
                        // right-left
                        case K:
                            animation.setLinearPath(50, stage.getHeight()/2,stage.getWidth() - 100, stage.getHeight()/2);
                            break;

                        // speed down
                        case O:
                            animation.changeSpeed(speedDown());
                            break;
                        // speed up
                        case P:
                            animation.changeSpeed(speedUp());
                            break;

                        // decries circle and ring size
                        case R:
                            vectorRingModel.resize(sizeDown());
                            break;
                        // increase circle and ring size
                        case T:
                            vectorRingModel.resize(sizeUp());
                            break;
                    }
                }
            });
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
