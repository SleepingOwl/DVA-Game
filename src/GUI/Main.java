package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Main.count = count;
    }

    public double getRingSize() {
        return ringSize;
    }

    public void setRingSize(double ringSize) {
        this.ringSize = ringSize;
    }

    public int getRingSpeed() {
        return ringSpeed;
    }

    public void setRingSpeed(int ringSpeed) {
        this.ringSpeed = ringSpeed;
    }

    private static int count = 0;
    private double ringSize;
    private int ringSpeed;
    enum DIRECTION {UP_DOWN, DOWN_UP, LEFT_RIGHT, RIGHT_LEFT}


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        primaryStage.setTitle("DVA");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
