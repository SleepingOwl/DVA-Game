package controllers.animation;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class PopUpController {

    private Stage dialog;

    private AnimationSceneController animationSceneController;

    void setStage(Stage stage){
        dialog = stage;
    }
    void setController(AnimationSceneController controller) { animationSceneController = controller; }

    @FXML
    private void arrowsHandler(KeyEvent keyEvent) throws IOException{
        try {
            switch (keyEvent.getCode()) {
                case UP:
                    if(animationSceneController.getAngle() == 0)
                        animationSceneController.incrementTrue();
                    break;
                case DOWN:
                    if(animationSceneController.getAngle() == 180)
                        animationSceneController.incrementTrue();
                    break;
                case LEFT:
                    if(animationSceneController.getAngle() == 270)
                        animationSceneController.incrementTrue();
                    break;
                case RIGHT:
                    if(animationSceneController.getAngle() == 90)
                        animationSceneController.incrementTrue();
                    break;
            }
        }
        finally {
            animationSceneController.animation.stop();
            animationSceneController.animation.play();
            animationSceneController.flickerRing.stop();
            animationSceneController.flickerRing.play();
            animationSceneController.vectorRingModel.setAllVisible(true);
            dialog.close();
        }
    }
}
