package Main;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

import java.util.Random;

public class VectorRingModel {

    private Circle redCircle;
    private StackPane movingPane;
    private SVGPath vectorRing;
    private int rotationAngle;

    public void setRandomRotationAngle() {
        this.rotationAngle = new Random().nextInt(4)*90;
    }

    public int getRotationAngle() {
        return rotationAngle;
    }

    public StackPane getMovingPane() {
        return movingPane;
    }

    public double getSizeX(){
        return vectorRing.getScaleX();
    }

    public void resize(double size){
        redCircle.setScaleX(size+0.2);
        vectorRing.setScaleX(size);
        redCircle.setScaleY(size+0.2);
        vectorRing.setScaleY(size);
    }

    public void hideRing(){
        vectorRing.setFill(redCircle.getFill());
    }

    public void showRing() {
        vectorRing.setFill(Color.BLACK);
        setRandomRotationAngle();
        vectorRing.setRotate(rotationAngle);
    }

    public void hideAll() {
        if(!movingPane.getChildren().isEmpty()){
            movingPane.getChildren().clear();
        }
    }

    public void showAll() {
        if(movingPane.getChildren().isEmpty()){
            movingPane.getChildren().addAll(redCircle, vectorRing);
        }
    }

    public void rotateRing(double angle) {
        vectorRing.setRotate(angle);
    }

    public VectorRingModel() {
        movingPane = new StackPane();
        movingPane.setAlignment(Pos.CENTER);
        redCircle = new Circle(0, 0, 30, Color.rgb(255, 0, 0));
        String svgString = "M30,7.9c7.2,2.1,12.6,9,12.6,16.9c0,10.3-8.8,18.5-19.2,17.6c-7.9-0.7-14.5-6.9-15.7-14.8" +
                " C6.2,18.5,11.9,10.2,20,7.9V0C8.6,2.3,0,12.6,0,24.8C0,38.8,11.2,50,25,50s25-11.3,25-25.2C50,12.6,41.4,2.3,30,0V7.9z".replace(',',' ');
        vectorRing = new SVGPath();
        vectorRing.setContent(svgString);

        movingPane.getChildren().addAll(redCircle, vectorRing);
    }

    public VectorRingModel(double centreX, double centreY) {
        movingPane = new StackPane();
        movingPane.setAlignment(Pos.CENTER);
        redCircle = new Circle(0, 0, 30, Color.rgb(255, 0, 0));
        String svgString = "M30,7.9c7.2,2.1,12.6,9,12.6,16.9c0,10.3-8.8,18.5-19.2,17.6c-7.9-0.7-14.5-6.9-15.7-14.8" +
                " C6.2,18.5,11.9,10.2,20,7.9V0C8.6,2.3,0,12.6,0,24.8C0,38.8,11.2,50,25,50s25-11.3,25-25.2C50,12.6,41.4,2.3,30,0V7.9z".replace(',',' ');
        vectorRing = new SVGPath();
        vectorRing.setContent(svgString);

        movingPane.getChildren().addAll(redCircle, vectorRing);
        movingPane.setLayoutX(centreX);
        movingPane.setLayoutY(centreY);
    }
}
