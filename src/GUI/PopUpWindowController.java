package GUI;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class PopUpWindowController {
    private int angle;
    private boolean exit = false;

    @FXML
    private void arrowInput(KeyEvent event) {
        switch (event.getCode()){
            case UP:
                angle = 0;
                break;
            case DOWN:
                angle = 180;
                break;
            case LEFT:
                angle = 270;
                break;
            case RIGHT:
                angle = 90;
                break;
            default:
                angle = 20;
                break;
        }
    }

    private void setAndExit(int angle){

    }

    public int getAngle() {
        return angle;
    }
}
