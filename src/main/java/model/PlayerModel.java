/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
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
import controller.PlayerController;

import java.awt.*;


/**
 * PlayerModel class is responsible for all the implementations regarding the player.
 */
public class PlayerModel extends PlayerController {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static Color INNER_COLOR = Color.GREEN;

    private static int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    /**
     * PlayerModel is a Parameterized Constructor that handles the initial implementation of the player.
     * Handles the player movement amount when game begins. Sets it to zero initially.
     * Creates the player.
     * Sets the min and max variables to set the dimensions of the player.
     * @param ballPoint     the location of the ball.
     * @param width         the width of the player.
     * @param height        the height of the player.
     * @param container     the rectangular container/frame of the game screen.
     */
    public PlayerModel(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makePlayer(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * makePlayer is a Private Method that creates the player of rectangular shape.
     * @param width     the width of the player.
     * @param height    the height of the player.
     * @return          returns a Rectangle player based on the dimensions specified.
     */
    private Rectangle makePlayer(int width, int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * impact is a Method that implements the impact of the ball on the platform.
     * @param b     passing in the Object/Reference variable of the BallController class. Aggregation relationship.
     * @return      returns a boolean value denoting if an impact has occurred or not.
     */
    public boolean playerImpact(BallController b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    /**
     * playerMove is a Method that implements the movement of the ball and the platform.
     */
    public void playerMove(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * playerMoveLeft is a Method that handles the movement of the player to the left.
     * Sets the move amount and direction when move left.
     */
    public void playerMoveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * playerMoveRight is a Method that handles the movement of the player to the right.
     * Sets the move amount and direction when move right.
     */
    public void playerMoveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * playerStop if a Method sets the movement amount to zero to stop the player movement.
     */
    public void playerStop(){
        moveAmount = 0;
    }

    /**
     * getPlayerFace is a Method that returns the playerFace.
     * @return      the shape of the player
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * playerMoveTo is a Method that sets the location of the ball and player.
     * @param p
     */
    public void playerMoveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * Setter Method for the INNER_COLOR of the player. Encapsulation
     * @param innerColor        inner color of the player
     */
    public static void setInnerColor(Color innerColor) {
        PlayerModel.INNER_COLOR = innerColor;
    }

    /**
     * Getter Method to get the inner color of player.
     * @return      returns the inner color of the player
     */
    public Color getInnerColor() {
        return INNER_COLOR;
    }

    /**
     * Getter Method to get the player movement amount.
     * @return      returns the player movement amount
     */
    public int getDefMoveAmount() {
        return DEF_MOVE_AMOUNT;
    }

    /**
     * Setter Method to set the player movement amount.
     * @param defMoveAmount     movement amount of player
     */
    public static void setDefMoveAmount(int defMoveAmount) {
        PlayerModel.DEF_MOVE_AMOUNT = defMoveAmount;
    }

}
