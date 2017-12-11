package controllers;

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

//  TODO сделать запуск анимации по нажатию стрелок
//  TODO запись информации о нажатой клавише
    @FXML
    private void arrowsHandler(KeyEvent keyEvent) throws IOException{
        try {
            switch (keyEvent.getCode()) {
                case UP:
                    if(animationSceneController.getAngle() == 0)
                        animationSceneController.incrementTrue();
                    animationSceneController.out.write(" UP\n");
                    break;
                case DOWN:
                    if(animationSceneController.getAngle() == 180)
                        animationSceneController.incrementTrue();
                    animationSceneController.out.write(" DOWN\n");
                    break;
                case LEFT:
                    if(animationSceneController.getAngle() == 270)
                        animationSceneController.incrementTrue();
                    animationSceneController.out.write(" LEFT\n");
                    break;
                case RIGHT:
                    if(animationSceneController.getAngle() == 90)
                        animationSceneController.incrementTrue();
                    animationSceneController.out.write(" RIGHT\n");
                    break;
            }
        }
        finally{
            animationSceneController.flickerRing.play();
            animationSceneController.vectorRingModel.showAll();
            dialog.close();
        }
    }
}
