package controller;

import java.awt.*;

/**
 * PlayerController class is an abstract class added in for extendability.
 *
 * @author Harini Manikandan
 */
abstract public class PlayerController {
    public abstract boolean impact(BallController b);
    public abstract void move();
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void stop();
    public abstract void moveTo(Point p);
}
