package controller;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * Crack class is a Nested Class under the BrickController abstract class as it is a feature of the Brick class.
 * Responsible for all the implementations regarding the crack generated when a ball makes an impact with a brick.
 */
public class CrackController {

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;


    private final BrickController brickController;
    private GeneralPath crack;

    private int crackDepth;
    private int steps;


    /**
     * Crack is a Parameterized Constructor that creates the crack.
     * Sets a randomly generated path from the point of impact.
     * Sets the depth of the crack.
     * Sets the crack steps.
     *
     * @param crackDepth        the depth of the crack
     * @param steps             the crack steps
     */
    public CrackController(BrickController brickController, int crackDepth, int steps) {
        this.brickController = brickController;

        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }


    /**
     * draw Method is responsible for drawing out the crack.
     *
     * @return      returns the crack
     */
    public GeneralPath draw() {

        return crack;
    }

    /**
     * reset is a Method to reset the crack.
     */
    public void reset() {
        crack.reset();
    }

    /**
     * makeCrack is responsible for setting the start and end location of the crack.
     *
     * @param point     the point of impact.
     * @param direction the direction of the crack.
     */
    public void makeCrack(Point2D point, int direction) {
        Rectangle bounds = brickController.brickFace.getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();


        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);

                break;

        }
    }

    /**
     * makeCrack Method is responsible for making a randomly generated path from the start point to the end point.
     *
     * @param start     start point of crack
     * @param end       end point of crack
     */
    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;

        int bound = crackDepth;
        int jump = bound * 5;

        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);

        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    /**
     * randomInBounds is a Private Method that is responsible for returning a random number
     * between the bound value and the negative bound value.
     *
     * @param bound     the bound value.
     * @return returns a random integer value between the bound value and the negative bound value.
     */
    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return BrickController.getRnd().nextInt(n) - bound;
    }

    /**
     * inMiddle is a Private Method responsible for checking if in the middle of the brick.
     * @param i             integer checking
     * @param steps         crack steps
     * @param divisions     divisions of brick
     * @return              returns a boolean value to check if in Middle or not.
     */
    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    /**
     * jump is a Private Method.
     * If getRnd() is greater than probability return a random interger value
     * Else do nothing.
     * @param bound         brick bounds
     * @param probability   brick probability
     * @return              returns the value from the method randomInBounds.
     */
    private int jumps(int bound, double probability) {
        if (BrickController.getRnd().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;
    }

    /**
     * makeRandomPoint is a Private method that returns a random point.
     * @param from          the start point
     * @param to            the end point
     * @param direction     the direction
     * @return              sets the location
     */
    private Point makeRandomPoint(Point from, Point to, int direction) {

        Point out = new Point();
        int pos;

        switch (direction) {
            case HORIZONTAL:
                pos = BrickController.getRnd().nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = BrickController.getRnd().nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
        }
        return out;
    }

}
