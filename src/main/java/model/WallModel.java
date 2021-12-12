/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Harini Manikandan
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

import controller.BallController;
import controller.BrickController;
import controller.CrackController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * WallModel class is responsible for all the implementations on the wall,ball and the impacts.
 */
public class WallModel {

    private Random rnd;
    private Rectangle area;

    public BrickController[] bricks;

    public BallController ball;
    public PlayerModel player;

    private Point startPoint;

    private static int brickBroken;
    private int brickCount;
    private int ballCount;

    private boolean ballLost;


    /**
     * WallModel is a Parameterized Constructor that hat handles the initial implementation of the wall.
     * @param drawArea      rectangle game area
     * @param ballPos       initial location of ball.
     */
    public WallModel(Rectangle drawArea, Point ballPos){

        this.startPoint = new Point(ballPos);

        //number of balls
        setBallCount(3);
        setBallLost(false);//ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);

        player = new PlayerModel((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;



    }



    /**
     * makeBall is a Private Method that calls the RubberBall Constructor to make the rubber ball.
     * Composition relationship
     * @param ballPos   the initial location of the ball.
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBallModel(ballPos);
    }



    /**
     * move Method calls the move methods in the Player and BallController classes.
     */
    public void move(){
        player.playerMove();
        ball.move();
    }

    /**
     * findImpacts Method is responsible for implementing all ball and brick properties when impact is made.
     * Implements when ball makes impact with the player.
     * Implements when ball makes impact with the wall. Calls the impactWall() method.
     * Implements when ball makes impact with the game frame/border.
     */
    public void findImpacts(){
        if(player.playerImpact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            brickCount--;
            //Incrementing total number of Bricks Broken when impact made.
            setBrickBroken(getBrickBroken()+1);
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            setBallCount(getBallCount()-1);
            setBallLost(true);//ballLost = true;
        }
    }

    /**
     * impactWall is a Private Method that is responsible for when the ball makes impact with the wall.
     * @return  returns a boolean value to denote if ball made impact with wall or not.
     */
    private boolean impactWall(){
        for(BrickController b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case BrickController.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, CrackController.UP);
                case BrickController.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up, CrackController.DOWN);

                //Horizontal Impact
                case BrickController.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right, CrackController.RIGHT);
                case BrickController.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left, CrackController.LEFT);
            }
        }
        return false;
    }

    /**
     * impactBorder is a Private Method that carries out the implementation for when the ball makes impact with game border.
     * @return      returns a boolean value to denote if ball made impact with the border or not.
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * Getter method for getting the brick count.
     * @return  returns the number of bricks from brickCount variable.
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * Getter method for getting the number of balls left.
     * @return  returns the number of balls from ballCount.
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * Setter method for setting the number of balls in game.
     * @param ballCount     number of balls.
     */
    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }

    /**
     * Getter method for finding if ball lost or not.
     * @return  returns a boolean value of ballLost variable.
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * Setter method for finding if ball lost or not.
     * @param ballLost  ball lost or not variable.
     */
    public void setBallLost(boolean ballLost) {
        this.ballLost = ballLost;
    }

    public void ballReset(){
        player.playerMoveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        setBallLost(false);//ballLost = false;
    }

    /**
     * wallReset method resets the wall.
     * Repairs all bricks.
     * Resets ball and brick count.
     */
    public void wallReset(){
        for(BrickController b : bricks)
            b.repair();
        brickCount = bricks.length;
        setBallCount(3);

    }

    /**
     * ballEnd() method checks if the all the balls have been used up.
     * @return  returns a boolean value based on the ballCount value.
     *          <code>true</code> if the ballCount is zero.
     *          <code>false</code> otherwise.
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * getBricks() method returns the brick.
     * @return      returns the bricks.
     */
    public BrickController[] getBricks() {
        return bricks;
    }

    /**
     * setBricks() method sets the bricks.
     * @param bricks    the bricks
     */
    public void setBricks(BrickController[] bricks) {
        this.bricks = bricks;
    }

    /**
     * Setter method to set the brick count on the wall.
     * @param brickCount    the number of bricks.
     */
    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }

    /**
     * isDone() method checks if the all the bricks have been broken.
     * @return  returns a boolean value based on the brickCount value.
     *          <code>true</code> if the ballCount is zero.
     *          <code>false</code> otherwise.
     */
    public boolean isDone(){
        return brickCount == 0;
    }


    /**
     * Setter method to set the speed of the ball in the x-axis direction.
     * @param s     speed value in x-axis direction
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * Setter method to set the speed of the ball in the y-axis direction.
     * @param s     speed value in y-axis direction
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * resetBallCount method resets the ball count back to full capacity, 3.
     */
    public void resetBallCount(){
        setBallCount(3);
    }

    /**
     * Getter method for the brickBroken variable.
     * @return  returns total number of bricks broken.
     */
    public static int getBrickBroken() {
        return brickBroken;
    }

    /**
     * Setter method for the brickBroken variable.
     * @param brickBroken   total number of bricks broken.
     */
    public static void setBrickBroken(int brickBroken) {
        WallModel.brickBroken = brickBroken;
    }


}
