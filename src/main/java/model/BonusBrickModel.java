package model;

import controller.BrickController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Timer;

public class BonusBrickModel extends BrickController {

    private static final String NAME = "Bonus Brick";
    private static final Color DEF_INNER = new Color(255, 51, 119);
    private static final Color DEF_BORDER = new Color(121, 64, 191);
    private static final int BONUS_STRENGTH = 1;
    //private WallModel wall;

    public BonusBrickModel(Point pos, Dimension size) {
        super(NAME,pos,size,DEF_BORDER,DEF_INNER,BONUS_STRENGTH);
        //this.wall = wall;
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
        WallModel.setBrickBroken(WallModel.getBrickBroken() + 3);
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }
}
