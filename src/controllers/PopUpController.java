package controllers;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class PopUpController {

    Stage dialog;

    void setStage(Stage stage){
        dialog = stage;
    }
//  TODO сделать запуск анимации по нажатию стрелок
//  TODO запись информации о нажатой клавише
    @FXML
    private void arrowsHandler(KeyEvent keyEvent){
        switch (keyEvent.getCode()){
            case UP:
                System.out.println("UP");
//                if(vectorRingModel.trueRotate(0))
//                    ++countTrue;
//                vectorRingModel.showAll();
//                flickerRing.play();
                dialog.close();
                break;
            case DOWN:
                System.out.println("DOWN");
                dialog.close();
                break;
            case LEFT:
                System.out.println("LEFT");
                dialog.close();
                break;
            case RIGHT:
                System.out.println("RIGHT");
                dialog.close();
                break;
        }
    }
}
