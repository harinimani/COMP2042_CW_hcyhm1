package model;

import controller.BrickController;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * ClayBrickModel class is SubClass/Child Class of the BrickController class.
 * It extends the BrickController class. Inheritance
 * Responsible for all the implementations regarding the Clay Brick.
 */
public class ClayBrickModel extends BrickController {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * ClayBrickModel is a Parameterized Constructor that runs the Parent Class's, BrickController class constructor.
     * @param point     brick position/location.
     * @param size      size of the brick.
     */
    public ClayBrickModel(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * makeBrickFace overrides the makeBrickFace in the parent class.
     * This method creates the Clay brick.
     * @param pos   the position/location of the brick.
     * @param size  size of the brick.
     * @return      returns the brick.
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * setImpact is an overridden method from the parent class.
     * Responsible for knowing if an impact has occurred or not.
     * Calls the BrickController's impact() method.
     * @param point point of impact.
     * @param dir   direction of impact.
     * @return      returns a boolean value stating if an impact has occurred or not.
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        impact();
        return true;
    }

    /**
     * getBrick implements the Abstract method from the parent class.
     * @return      returns the Clay Brick.
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
