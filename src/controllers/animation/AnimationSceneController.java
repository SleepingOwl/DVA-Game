package controllers.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.AnimationModel;
import models.VectorRingModel;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
public class AnimationSceneController implements Initializable {

    private Stage stage;

    @FXML AnchorPane anchorPane;
    @FXML PopUpController popUpController;
    @FXML HBox topDialog;
    @FXML BorderPane bp;
    @FXML ColorPicker backgroundColor;
    @FXML Label speedInfo;
    @FXML Label sizeInfo;

    private int resultId;

    AnimationModel animation;
    VectorRingModel vectorRingModel;
    Timeline flickerRing;
    private int angle;
    private double hbPrefHeight = 0.0;

    private int showTime;
    private int ringLifetime;

    private KeyFrame startFrame;
    private KeyFrame endFrame;

    private static int countTrue = 0;
    private static int count = 0;

    private static double speedMultiply = 1;
    private static double sizeMultiply = 1;

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        vectorRingModel = new VectorRingModel();
        vectorRingModel.randomRotation();
        anchorPane.getChildren().addAll( vectorRingModel.getMovingPane() );

        animation = new AnimationModel(vectorRingModel.getMovingPane());
        animation.play();

        vectorRingModel.setRingVisible(false);
        showTime = new Random().nextInt(5000)+1000;
        ringLifetime = 500;
        flickerRing = new Timeline();
        startFrame = new KeyFrame(Duration.millis(showTime), (e) -> vectorRingModel.setRingVisible(true));
        endFrame = new KeyFrame(Duration.millis(showTime+ringLifetime), (e)-> hideAndShuffle());
        flickerRing.setCycleCount(Timeline.INDEFINITE);
        flickerRing.getKeyFrames().setAll( startFrame, endFrame );
        flickerRing.play();

        bp.setTop( null );
        hbPrefHeight = topDialog.getPrefHeight();

        speedInfo.textProperty().bind(new SimpleDoubleProperty(animation.getSpeed()).asString());
//        sizeInfo.textProperty().bind(new SimpleDoubleProperty().asString());
    }

    @FXML
    public void handleTopDialog(MouseEvent event){
        if(event.isSecondaryButtonDown()) {

            topDialog.setPrefHeight(0.0);
            bp.setTop(topDialog);

            Timeline openTimeline = new Timeline();
            openTimeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(topDialog.prefHeightProperty(), 0)),
                    new KeyFrame(Duration.millis(300.0), new KeyValue(topDialog.prefHeightProperty(), hbPrefHeight))
            );
            openTimeline.play();
        }
        else if(event.isPrimaryButtonDown()) {
            Timeline closeTimeline = new Timeline();
            closeTimeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(topDialog.prefHeightProperty(), hbPrefHeight)),
                    new KeyFrame(Duration.millis(300.0), new KeyValue(topDialog.prefHeightProperty(), 0))
            );
            closeTimeline.play();

            closeTimeline.setOnFinished(e -> bp.setTop( null ));

        }
        bp.requestFocus();
    }
    @FXML
    void choiceColor(ActionEvent event){
        anchorPane.setBackground(new Background(new BackgroundFill(backgroundColor.getValue(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    public void keyboardHandler(KeyEvent keyEvent) throws IOException{
        switch (keyEvent.getCode()){
            case SPACE:
                angle = vectorRingModel.getRotationAngle();
//                System.out.println(angle);
                vectorRingModel.rotateRing(2.0);
                vectorRingModel.setAllVisible(false);
                flickerRing.stop();
                animation.stop();
                ++count;

                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/animation/pop-up.fxml"));
                Parent root = loader.load();
                popUpController = loader.getController();
                popUpController.setStage(dialog);
                popUpController.setController(this);
                Scene dialogScene = new Scene(root);
                dialog.setScene(dialogScene);
                dialog.show();
                dialogScene.getRoot().requestFocus();

//                System.out.println(count);
//                System.out.println(countTrue);
                double speed = animation.getSpeed();
                double size = vectorRingModel.getSizeX();
                String direction;
                switch (angle){
                    case 90:
                        direction = "RIGHT";
                        break;
                    case 180:
                        direction = "DOWN";
                        break;
                    case 270:
                        direction = "LEFT";
                        break;
                    case 0:
                        direction = "UP";
                        break;
                    default:
                        direction = "UNKNOWN";
                        break;
                }
                break;

            // up-down
            case NUMPAD8:
                animation.setLinearPath(stage.getWidth()/2, 50,stage.getWidth()/2, stage.getHeight() - 100);
                break;
            // right-left
            case NUMPAD6:
                animation.setLinearPath(stage.getWidth() - 100,  stage.getHeight()/2,50, stage.getHeight()/2);
                break;
            // down-up
            case NUMPAD2:
                animation.setLinearPath(stage.getWidth()/2, stage.getHeight()-100,stage.getWidth()/2, 50);
                break;
            // left-right
            case NUMPAD4:
                animation.setLinearPath(50, stage.getHeight()/2,stage.getWidth() - 100, stage.getHeight()/2);
                break;

            // decries circle and ring size
            case SUBTRACT:
                vectorRingModel.resize(sizeDown());
                //sizeUp();
                break;
            // increase circle and ring size
            case ADD:
                vectorRingModel.resize(sizeUp());
                break;

            // speed down
            case O:
                animation.changeSpeed(speedDown());
                speedInfo.textProperty().bind(new SimpleDoubleProperty(animation.getSpeed()).asString());
                break;
            // speed up
            case P:
                animation.changeSpeed(speedUp());
                speedInfo.textProperty().bind(new SimpleDoubleProperty(animation.getSpeed()).asString());
                break;

            case I:
                ringLifetime += 50;
                break;
            case U:
                if(ringLifetime < 100)
                    ringLifetime = 50;
                else
                    ringLifetime -= 50;
                break;
            case R:
                animation.stop();
                animation.play();
                break;

//            case ESCAPE:
//                double result = ( (double)countTrue/ (double)count);
//                out.write("Процент попадания: " + result*100 + "; Последняя скорость: " + animation.getSpeed());
//                count = 0;
//                countTrue = 0;
//                animation.stop();
//                flickerRing.stop();
//                stage.close();
//                out.close();
//                break;
        }
    }

    private void hideAndShuffle(){
        showTime = new Random().nextInt(5000)+1000;

        if(flickerRing != null) flickerRing.stop();

        startFrame = new KeyFrame(Duration.millis(showTime), (e) -> vectorRingModel.setRingVisible(true));
        endFrame = new KeyFrame(Duration.millis(showTime+ringLifetime), (e)-> hideAndShuffle());
        flickerRing.getKeyFrames().setAll(startFrame, endFrame);
        flickerRing.play();

        vectorRingModel.randomRotation();
        vectorRingModel.setRingVisible(false);
    }

    private static double speedUp() {
        speedMultiply = speedMultiply + 0.2;
        return speedMultiply;
    }

    private static double speedDown() {
        if (speedMultiply <= 0.1)
            return 0.1;
        else {
            speedMultiply = speedMultiply - 0.2;
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

    int getAngle(){
        return angle;
    }

    public void setStage(Stage stage){
        this.stage = stage;
        Stage test = (Stage) anchorPane.getScene().getWindow();
        animation.setLinearPath(50, test.getHeight()/2,test.getWidth()-100, test.getHeight()/2);
    }

    void incrementTrue(){
        ++countTrue;
    }
}



