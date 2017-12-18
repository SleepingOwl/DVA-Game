package Main;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RingModel {

    private ImageView ring = loadImage("Landolt_Ring.png");
    private Circle redCircle;
    private StackPane movingPane;

    public StackPane getMovingPane() {
        return movingPane;
    }

    public ImageView getRing(){
        return ring;
    }

    private ImageView loadImage(String name) {
        Image img = new Image(getClass().getResourceAsStream("../resources/image/" + name));
        ImageView ring = new ImageView(img);
        return ring;
    }

    void resize(double size){
        redCircle.setScaleX(size);
        redCircle.setScaleY(size);
        ring.setScaleX(size);
        ring.setScaleY(size);
        ring.setPreserveRatio(true);
        ring.setSmooth(true);
        ring.setCache(true);
    }

    RingModel() {
        movingPane = new StackPane();
        movingPane.setAlignment(Pos.CENTER);
        redCircle = new Circle(0, 0, 100, Color.rgb(255, 0, 0));

        movingPane.getChildren().addAll(redCircle, ring);
    }

    RingModel(double centreX, double centreY) {
        movingPane = new StackPane();
        movingPane.setAlignment(Pos.CENTER);
        redCircle = new Circle(0, 0, 100, Color.rgb(255, 0, 0));

        movingPane.getChildren().addAll(redCircle, ring);
        movingPane.setLayoutX(centreX);
        movingPane.setLayoutY(centreY);
    }
}
