package model;

import controller.BrickController;
import controller.GameTimeController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Timer;

public class TimesaverBrickModel extends BrickController {

    private static final String NAME = "Time Saver Brick";
    private static final Color DEF_INNER = new Color(33, 158, 188);
    private static final Color DEF_BORDER = Color.BLUE;
    private static final int TIME_SAVER_STRENGTH = 1;
    private GameTimeController displayTime;


    public TimesaverBrickModel(Point pos, Dimension size) {
        super(NAME,pos,size,DEF_BORDER,DEF_INNER,TIME_SAVER_STRENGTH);
        displayTime = new GameTimeController();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        impact();
        return true;
    }

    public void impact(){
        super.impact();
        if(displayTime.getSeconds() >= 5)
            displayTime.setSeconds(displayTime.getSeconds()-5);
        else{
            if(displayTime.getMinutes()>0){
                displayTime.setMinutes(displayTime.getMinutes() - 1);
                displayTime.setSeconds((displayTime.getSeconds()+60) - 5);
            }
        }
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }
}
