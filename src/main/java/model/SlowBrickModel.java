package model;

import controller.BrickController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

public class SlowBrickModel extends BrickController {

    private static final String NAME = "Slow Brick";
    private static final Color DEF_INNER = Color.YELLOW;
    private static final Color DEF_BORDER = Color.DARK_GRAY;
    private static final int SLOW_STRENGTH = 1;


    public SlowBrickModel(Point pos, Dimension size) {
        super(NAME,pos,size,DEF_BORDER,DEF_INNER,SLOW_STRENGTH);
    }

    public void slowPlayer(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PlayerModel.setInnerColor(Color.GREEN);
                PlayerModel.setDefMoveAmount(5);
            }
        },5000);
        PlayerModel.setDefMoveAmount(2);
        PlayerModel.setInnerColor(Color.RED);
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
        slowPlayer();
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }
}
