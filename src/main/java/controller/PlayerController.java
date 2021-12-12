package controller;

import java.awt.*;

/**
 * PlayerController class is an abstract class added in for extendability.
 *
 * @author Harini Manikandan
 */
abstract public class PlayerController {
    public abstract boolean playerImpact(BallController b);
    public abstract void playerMove();
    public abstract void playerMoveLeft();
    public abstract void playerMoveRight();
    public abstract void playerStop();
    public abstract void playerMoveTo(Point p);
}
