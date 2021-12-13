package controller;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 *  BrickController is an Abstract Class that handles all the implementations regarding the Bricks in the game.
 */
abstract public class BrickController {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private static Random rnd;

    private String name;
    protected Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * BrickController is a Parameterized Constructor that that handles the initial implementation of the brick.
     * Sets the brick name.
     * Sets the brick status as NOT BROKEN.
     * Creates the brick.
     * @param name      brick name
     * @param pos       brick position/location
     * @param size      size of the brick
     * @param border    brick border color
     * @param inner     brick inner color
     * @param strength  brick's strength
     */
    public BrickController(String name, Point pos, Dimension size, Color border, Color inner, int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.setStrength(strength);
        this.fullStrength = getStrength();

    }

    /**
     * makeBall is an Abstract Method which creates the ball.
     * This method will later be implemented by the child brick classes or any other extensions the programmer wishes to add
     * @param pos       the position/location of the brick.
     * @param size      size of the brick.
     * @return          returns the brick.
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * setImpact Method is responsible for letting know if an impact has occurred.
     * If brick is broken, will return false. Denoting no impact made.
     * Else, will run the impact method and after impact, return the if brick broken or not.
     * @param point     point of impact.
     * @param dir       direction of impact.
     * @return          returns a boolean value to state if brick is broken or not.
     */
    public abstract boolean setImpact(Point2D point , int dir);

    /**
     * getBrick is an Abstract Method that will be later implemented.
     * Returns the brick shape.
     * @return      returns the brick.
     */
    public abstract Shape getBrick();

    /**
     * getRnd Method is responsible for returning the Random object.
     * @return      returns a Random object.
     */
    public static Random getRnd() {
        return rnd;
    }

    /**
     * getBorderColor Method is responsible for returning border color of brick.
     * Encapsulation of the border variable.
     * @return      returns border color of brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * getBorderColor Method is responsible for returning border color of brick.
     * Encapsulation of the border variable.
     * @return      returns border color of brick
     */
    public Color getInnerColor(){
        return inner;
    }


    /**
     * findImpact Method is responsible for determining the direction of the impact from the ball on the brick.
     * @param b     passing in the Object/Reference variable of the BallController class. Aggregation relationship.
     * @return
     */
    public final int findImpact(BallController b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     * isBroken Method is responsible for returning if the brick is broken or not.
     * Encapsulation of the broken variable.
     * @return      returns the boolean value of broken variable.
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * repair Method is responsible for repairing the brick.
     * Sets the broken back to NOT broken.
     * Sets strength to full capacity.
     */
    public void repair() {
        broken = false;
        setStrength(fullStrength);
    }

    /**
     * impact Method is responsible to set the broken variable based on the brick's status.
     * Also responsible for deducting the strength of a brick when an impact has occurred.
     */
    public void impact(){
        setStrength(getStrength()-1);
        broken = (getStrength() == 0);
    }

    /**
     * Getter method for the strength variable. Encapsulation
     * @return      returns the strength value of the ball
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Setter method for the strength variable. Encapsulation
     * Sets the value of the private variable strength
     * @param strength      strength value of brick
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

}





