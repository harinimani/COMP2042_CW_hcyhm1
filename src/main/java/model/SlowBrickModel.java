package model;

import controller.BrickController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

/**
 * SlowBrickModel class is SubClass/Child Class of the BrickController class.
 * It extends the BrickController class. Inheritance
 * Responsible for all the implementations regarding the Slow Brick.
 *
 * @author Harini Manikandan
 */
public class SlowBrickModel extends BrickController {

    private static final String NAME = "Slow Brick";
    private static final Color DEF_INNER = Color.YELLOW;
    private static final Color DEF_BORDER = Color.DARK_GRAY;
    private static final int SLOW_STRENGTH = 1;


    /**
     * BonusBrickModel is a Parameterized Constructor that runs the Parent Class's, BrickController class constructor.
     *
     * @param pos       brick position/location.
     * @param size      size of brick.
     */
    public SlowBrickModel(Point pos, Dimension size) {
        super(NAME,pos,size,DEF_BORDER,DEF_INNER,SLOW_STRENGTH);
    }


    /**
     * slowPlayer() Method slows the player movement for 5 seconds.
     * Reduces the movement amount to 2.
     * Changes the player color to indicate it is in Slow mode.
     */
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

    /**
     * makeBrickFace overrides the makeBrickFace in the parent class.
     * This method creates the Slow brick.
     * @param pos  the position/location of the brick.
     * @param size size of the brick.
     * @return     returns the Slow brick.
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
     * <li>Calls the slowPlayer() method.
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
            slowPlayer();
            return true;
        }
    }

    /*public void impact(){
        super.impact();
        slowPlayer();
    }*/

    /**
     * getBrick implements the Abstract method from the parent class.
     * @return      returns the Slow brick.
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }
}
