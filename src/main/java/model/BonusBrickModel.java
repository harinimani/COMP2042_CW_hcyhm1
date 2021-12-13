package model;

import controller.BrickController;
import controller.WallController;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * BonusBrickModel class is SubClass/Child Class of the BrickController class.
 * It extends the BrickController class. Inheritance
 * Responsible for all the implementations regarding the Bonus Brick.
 *
 * @author Harini Manikandan
 */
public class BonusBrickModel extends BrickController {

    private static final String NAME = "Bonus Brick";
    private static final Color DEF_INNER = new Color(255, 51, 119);
    private static final Color DEF_BORDER = new Color(121, 64, 191);
    private static final int BONUS_STRENGTH = 1;


    /**
     * BonusBrickModel is a Parameterized Constructor that runs the Parent Class's, BrickController class constructor.
     *
     * @param pos       brick position/location.
     * @param size      size of the brick.
     */
    public BonusBrickModel(Point pos, Dimension size) {
        super(NAME,pos,size,DEF_BORDER,DEF_INNER,BONUS_STRENGTH);
    }

    /**
     * makeBrickFace overrides the makeBrickFace in the parent class.
     * This method creates the Bonus brick.
     * @param pos  the position/location of the brick.
     * @param size size of the brick.
     * @return      returns the Bonus brick.
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * setImpact is an overridden method from the parent class.
     * Responsible for knowing if an impact has occurred or not.
     * If brick not broken:
     * <ul>
     * <li>Calls the parent class impact() method.
     * <li>Increments the Total Brick Broken by 4
     * <li>returns true, indicating impact made.
     * </ul>
     * @param point point of impact.
     * @param dir   direction of impact.
     * @return      returns a boolean value stating if an impact has occurred or not.
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        else {
            impact();
            WallController.setBrickBroken(WallController.getBrickBroken() + 3);
            return true;
        }
    }

    /**
     * getBrick implements the Abstract method from the parent class.
     * @return      returns the Bonus Brick.
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }
}
