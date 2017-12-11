package controllers;

import GUI.AnimationModel;
import GUI.OutputStatistic;
import GUI.VectorRingModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
//  TODO запсиь информации о скорости и размере
public class AnimationSceneController implements Initializable {

    private Stage stage;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    PopUpController popUpController;

    AnimationModel animation;
    VectorRingModel vectorRingModel;
    Timeline flickerRing;
    private static int countTrue = 0;
    private static int count = 0;

    private static double speedMultiply = 1;
    private static double sizeMultiply = 1;

    void setStage(Stage stage){
        this.stage = stage;
        Stage test = (Stage) anchorPane.getScene().getWindow();
        animation.setLinearPath(50, test.getHeight()/2,test.getWidth()-100, test.getHeight()/2);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        vectorRingModel = new VectorRingModel();
        anchorPane.getChildren().addAll( vectorRingModel.getMovingPane() );

        animation = new AnimationModel(vectorRingModel.getMovingPane());
        animation.play();

        vectorRingModel.hideRing();
        int timeToShow = new Random().nextInt(3)+1;
        flickerRing = new Timeline();
        flickerRing.setCycleCount(Timeline.INDEFINITE);
        flickerRing.getKeyFrames().addAll( new KeyFrame(Duration.seconds(timeToShow), (e) -> vectorRingModel.showRing()),
                new KeyFrame(Duration.seconds(timeToShow+0.5), (e)-> vectorRingModel.hideRing()) );
        flickerRing.play();

        try {
            OutputStatistic out = new OutputStatistic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void keyboardHandler(KeyEvent keyEvent) throws IOException{
        switch (keyEvent.getCode()){
            case SPACE:

                vectorRingModel.hideAll();
                vectorRingModel.hideRing();
                flickerRing.pause();
                ++count;

                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/pop-up.fxml"));
                Parent root = loader.load();
                popUpController = loader.getController();
                popUpController.setStage(dialog);
                Scene dialogScene = new Scene(root);
                dialog.setScene(dialogScene);
                dialog.show();
                dialogScene.getRoot().requestFocus();
                break;

            // up-down
            case U:
                animation.setLinearPath(stage.getWidth()/2, 50,stage.getWidth()/2, stage.getHeight() - 100);
                break;
            // left-right
            case K:
                animation.setLinearPath(stage.getWidth() - 100,  stage.getHeight()/2,50, stage.getHeight()/2);
                break;
            // down-up
            case J:
                animation.setLinearPath(stage.getWidth()/2, stage.getHeight()-100,stage.getWidth()/2, 50);
                break;
            // right-left
            case H:
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
                System.out.println(sizeMultiply);
                //sizeUp();
                break;
            // increase circle and ring size
            case T:
                vectorRingModel.resize(sizeUp());System.out.println(sizeMultiply);
                //System.out.println(size);
                break;
//            case ESCAPE:
//                animation.stop();
//                flickerRing.stop();
//                stage.close();
//                break;
        }
    }

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
            sizeMultiply= Double.valueOf(String.format("%.1f",sizeMultiply-0.1).replace(",","."));
            //sizeMultiply = sizeMultiply - 0.1;

        }return sizeMultiply;
    }

}



