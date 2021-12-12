package model;

import controller.BrickController;
import controller.GameTimeController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Timer;

/**
 * TimesaverBrickModel class is SubClass/Child Class of the BrickController class.
 * It extends the BrickController class. Inheritance
 * Responsible for all the implementations regarding the Slow Brick.
 *
 * @author Harini Manikandan
 */
public class TimesaverBrickModel extends BrickController {

    private static final String NAME = "Time Saver Brick";
    private static final Color DEF_INNER = new Color(33, 158, 188);
    private static final Color DEF_BORDER = Color.BLUE;
    private static final int TIME_SAVER_STRENGTH = 1;
    private GameTimeController displayTime;


    /**
     * TimesaverBrickModel is a Parameterized Constructor that runs the BrickController class constructor.
     *
     * @param pos       brick position/location.
     * @param size      size of brick.
     */
    public TimesaverBrickModel(Point pos, Dimension size) {
        super(NAME,pos,size,DEF_BORDER,DEF_INNER,TIME_SAVER_STRENGTH);
        displayTime = new GameTimeController();
    }

    /**
     * makeBrickFace overrides the makeBrickFace in the parent class.
     * This method creates the Bonus brick.
     * @param pos  the position/location of the brick.
     * @param size size of the brick.
     * @return      returns the brick.
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
     * <li>Deducts 5 seconds from the time taken.
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
        else{
            impact();
            if(displayTime.getSeconds() >= 5)
                displayTime.setSeconds(displayTime.getSeconds()-5);
            else{
                if(displayTime.getMinutes()>0){
                    displayTime.setMinutes(displayTime.getMinutes() - 1);
                    displayTime.setSeconds((displayTime.getSeconds()+60) - 5);
                }
            }
            return true;
        }
    }

    /**
     * getBrick implements the Abstract method from the parent class.
     * @return      returns the Time-Saver Brick
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }
}
