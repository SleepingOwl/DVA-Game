package GUI;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

public class VectorRingModel {

    private  Circle redCircle;
    private StackPane movingPane;
    private SVGPath vectorRing;

    public StackPane getMovingPane() {
        return movingPane;
    }

    void resize(double size){
        redCircle.setScaleX(size);
        vectorRing.setScaleX(size);
        redCircle.setScaleY(size);
        vectorRing.setScaleY(size);
    }

    VectorRingModel() {
        movingPane = new StackPane();
        movingPane.setAlignment(Pos.CENTER);
        redCircle = new Circle(0, 0, 30, Color.rgb(255, 0, 0));
        String svgString = "M29.9,8.2c7.1,2.1,12.3,8.8,12.3,16.6c0,9.6-7.7,17.3-17.2,17.3S7.8,34.3,7.8,24.8c0-7.8,5.2-14.5,12.3-16.6" +
                " V0.5C8.9,2.8,0.5,12.8,0.5,24.8c0,13.7,11,24.7,24.5,24.7s24.5-11.1,24.5-24.7c0-12-8.4-22-19.6-24.3V8.2z".replace(',',' ');
        vectorRing = new SVGPath();
        vectorRing.setContent(svgString);

        movingPane.getChildren().addAll(redCircle, vectorRing);
    }

    VectorRingModel(double centreX, double centreY) {
        movingPane = new StackPane();
        movingPane.setAlignment(Pos.CENTER);
        redCircle = new Circle(0, 0, 30, Color.rgb(255, 0, 0));
        String svgString = "M29.9,8.2c7.1,2.1,12.3,8.8,12.3,16.6c0,9.6-7.7,17.3-17.2,17.3S7.8,34.3,7.8,24.8c0-7.8,5.2-14.5,12.3-16.6" +
                " V0.5C8.9,2.8,0.5,12.8,0.5,24.8c0,13.7,11,24.7,24.5,24.7s24.5-11.1,24.5-24.7c0-12-8.4-22-19.6-24.3V8.2z".replace(',',' ');
        vectorRing = new SVGPath();
        vectorRing.setContent(svgString);

        movingPane.getChildren().addAll(redCircle, vectorRing);
        movingPane.setLayoutX(centreX);
        movingPane.setLayoutY(centreY);
    }
}
