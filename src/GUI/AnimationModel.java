package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class AnimationModel {

    private StackPane animationNode;
    private KeyFrame startFrame;
    private KeyFrame endFrame;
    private int speed;
    private Timeline timeline;
    AnimationModel(StackPane node){
        animationNode = node;
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
    }

    public void setEndPoint(double x, double y){
        final KeyFrame kf1 = new KeyFrame(Duration.millis(0),
                new KeyValue(animationNode.translateXProperty(), x),
                new KeyValue(animationNode.translateYProperty(), y));
        final KeyFrame kf2 = new KeyFrame(Duration.millis(300),
                new KeyValue(animationNode.translateXProperty(), x),
                new KeyValue(animationNode.translateYProperty(), y));

        timeline.getKeyFrames().addAll(kf1, kf2);
    }

    public void play(){
        timeline.play();
    }
}
