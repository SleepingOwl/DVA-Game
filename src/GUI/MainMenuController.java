package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class MainMenuController {

    @FXML
    private Button start;
    @FXML
    private Button controls;
    @FXML
    private Button authors;

    private static int countTrue = 0;
    private static int count = 0;

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
           sizeMultiply= Double.valueOf(String.format("%.1f",sizeMultiply-0.1).replace(",","."));
           //sizeMultiply = sizeMultiply - 0.1;

        }return sizeMultiply;
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

            VectorRingModel vectorRingModel = new VectorRingModel();
            root.getChildren().addAll( vectorRingModel.getMovingPane() );

            AnimationModel animation = new AnimationModel(vectorRingModel.getMovingPane());
            //animation.setLinearPath(50, stage.getMaxHeight()/2,stage.getMaxWidth()-100, stage.getHeight()/2);

            animation.play();

            vectorRingModel.hideRing();
            int timeToShow = new Random().nextInt(3)+1;
            Timeline flickerRing = new Timeline();
            flickerRing.setCycleCount(Timeline.INDEFINITE);
            flickerRing.getKeyFrames().addAll( new KeyFrame(Duration.seconds(timeToShow), (e) -> vectorRingModel.showRing()),
                                               new KeyFrame(Duration.seconds(timeToShow+1), (e)-> vectorRingModel.hideRing()) );
            flickerRing.play();

            scene.setOnKeyPressed(event1 -> {
                switch (event1.getCode()){
                    case SPACE:
                        vectorRingModel.hideAll();
                        vectorRingModel.hideRing();
                        flickerRing.pause();
                        ++count;
                        //int angle = vectorRingModel.getRotationAngle();

                        System.out.println("Count: "+count);
                        System.out.println("True count: "+countTrue);
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(stage);
                        VBox dialogVbox = new VBox(20);
                        dialogVbox.setAlignment(Pos.CENTER);
                        dialogVbox.getChildren().add(new Text("Укажите напрвление кольца стрелками на клавиатуре"));
                        Scene dialogScene = new Scene(dialogVbox, 400, 200);
                        dialog.setScene(dialogScene);
                        dialog.show();

                        dialogScene.setOnKeyPressed(keyEvent -> {
                            switch (keyEvent.getCode()){
                                case UP:
                                    if(vectorRingModel.trueRotate(0))
                                        ++countTrue;
                                    vectorRingModel.showAll();
                                    flickerRing.play();
                                    dialog.close();
                                    break;
                                case DOWN:
                                    if(vectorRingModel.trueRotate(180))
                                        ++countTrue;
                                    flickerRing.play();
                                    dialog.close();
                                    break;
                                case LEFT:
                                    if(vectorRingModel.trueRotate(270))
                                        ++countTrue;
                                    flickerRing.play();
                                    dialog.close();
                                    break;
                                case RIGHT:
                                    if(vectorRingModel.trueRotate(90))
                                        ++countTrue;
                                    flickerRing.play();
                                    dialog.close();
                                    break;
                            }
                        });
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
                }
            });
        }
        else if(event.getSource() == controls){
            stage = (Stage) controls.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/control-menu.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
}
