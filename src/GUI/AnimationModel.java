package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationModel {

    private Node animationNode;
    private KeyFrame startFrame;
    private KeyFrame endFrame;
    private double speed = 8000;
    private Timeline timeline;


    public AnimationModel(Node node) {
        animationNode = node;
        startFrame = new KeyFrame(Duration.millis(0), new KeyValue(animationNode.translateXProperty(), 100));
        endFrame = new KeyFrame(Duration.millis(speed), new KeyValue(animationNode.translateXProperty(), 800));
        timeline = new Timeline();
        timeline.getKeyFrames().setAll(startFrame, endFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
    }

    public double getSpeed() {
        return (int)speed;
    }

    public void changeSpeed(double xN){
        if (xN < 0.1) {
            speed = speed/0.1;
        }
        else {
            speed = speed/xN;
            timeline.setRate(xN);
//        timeline.jumpTo();
            timeline.play();
        }
    }

    public void setLinearPath(double startX, double startY, double endX, double endY){
        timeline.stop();
        startFrame = new KeyFrame(Duration.millis(0), new KeyValue(animationNode.translateXProperty(), startX), new KeyValue(animationNode.translateYProperty(), startY));
        endFrame = new KeyFrame(Duration.millis(speed), new KeyValue(animationNode.translateXProperty(), endX), new KeyValue(animationNode.translateYProperty(), endY));
        timeline.getKeyFrames().setAll(startFrame, endFrame);
        timeline.play();
    }

    public void play(){
        timeline.play();
    }
    public void stop() {timeline.stop();}
}
