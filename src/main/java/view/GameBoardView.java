/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021  Harini Manikandan
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
package view;

import controller.BallController;
import controller.BrickController;
import controller.GameTimeController;
import controller.ScoreController;
import model.LevelsModel;
import model.PlayerModel;
import model.WallModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * GameBoardView class displays the in-game screen and handles the in-game implementation.
 */
public class GameBoardView extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private WallModel wall;

    private String message;
    private String message2;

    private boolean showPauseMenu;

    private Font pauseMenuFont;

    private DebugConsoleView debugConsole;
    private PauseMenuView pauseMenu;
    private LevelsModel level;
    private GameTimeController displayTime;
    private ScoreController score;


    /**
     * GameBoardView is a parameterized constructor that displays the In-Game screen. Handles the in-game implementation.
     * Deals with the Wall implementation, the Debug Console implementation.
     * Sets the Game Timer.
     * @param owner     reads the owner of the system the game runs on.
     */
    public GameBoardView(JFrame owner){
        super();

        pauseMenu = new PauseMenuView();
        pauseMenu.setStrLen(0);
        showPauseMenu = false;



        pauseMenuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);


        this.initialize();
        message = "";
        message2 = "";
        wall = new WallModel(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),new Point(300,430));
        level = new LevelsModel(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,wall);

        displayTime = new GameTimeController();
        score = new ScoreController();
        debugConsole = new DebugConsoleView(owner,wall,level,this);
        //initialize the first level
        level.nextLevel();
        message = "Level "+level.getLevel();

        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();
            displayTime.setGameIsRunning(true);
            message = String.format("Bricks: %d  Balls: %d",wall.getBrickCount(),wall.getBallCount());
            message2 = String.format("Total Bricks Broken: %d  Timer: %02dm:%02ds", WallModel.getBrickBroken() ,displayTime.getMinutes(),displayTime.getSeconds());
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    displayTime.setGameIsRunning(false);
                    score.sortScores();
                    message = "Game over";
                }
                displayTime.setGameIsRunning(false);
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(level.hasLevel()){
                    message = "Go to Next Level: Level "+(level.getLevel()+1);
                    message2 ="";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    level.nextLevel();
                }
                else{
                    displayTime.setGameIsRunning(false);
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                    score.sortScores();
                }
            }

            repaint();
        });

    }


    /**
     * initialize is a Private Method to initialize the in-game screen.
     * Sets the in-game screen size, enables game screen to gain focus.
     * Listens and notifies when any keys are pressed, and if there are any mouse clicks or motions.
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    /**
     * paint is an Overridden Method from the JComponent class.
     * The method paints/draws the game board view.
     * Draws the message on screen.
     * Draws the ball, player and wall on the in-game screen.
     * Draws the Pause screen as well.
     * @param g
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);    //location of message on the screen
        g2d.drawString(message2,200,245);   //location of message2 on the screen

        drawBall(wall.ball,g2d);

        for(BrickController b : wall.bricks)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.player,g2d);

        if(showPauseMenu)
            pauseMenu.PauseMenuScreen(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * clear is a Private Method which is responsible for clearing the in-game screen.
     * @param g2d
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * drawBrick is a private Method that draws the brick to the in-game screen.
     * Sets and Draws the brick color, shape and type.
     * @param brick     passing in the Object/Reference variable of the BrickController class. Aggregation relationship.
     * @param g2d
     */
    private void drawBrick(BrickController brick, Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * drawBall is a private Method that draws the ball components to the in-game screen.
     * Draws the ball features such as color and shape
     * @param ball      passing in the Object/Reference variable of the BallController class. Aggregation relationship.
     * @param g2d
     */
    private void drawBall(BallController ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * drawPlayer is a private Method that draws the player components to the in-game screen.
     * Draws the player shape, color.
     * @param p     passing in the Object/Reference variable of the PlayerModel class. Aggregation relationship.
     * @param g2d
     */
    private void drawPlayer(PlayerModel p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(PlayerModel.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(PlayerModel.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }



    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * keyPressed implements the method in KeyListener.
     * Listens and notifies if a key is pressed.
     * Implements the next course of actions if specific keys are pressed, such as:
     * A: move player left, D: move player right, ESC: show pause menu, SPACE: pause game, ALT-SHIFT-F1: display DebugConsole.
     * @param keyEvent      to indicate if a key action has occurred or not.
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                wall.player.playerMoveLeft();
                break;
            case KeyEvent.VK_D:
                wall.player.playerMoveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                displayTime.setGameIsRunning(false);
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning()){
                        displayTime.setGameIsRunning(false);
                        gameTimer.stop();
                    }
                    else{
                        gameTimer.start();
                        displayTime.setGameIsRunning(true);
                    }
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown()){
                    debugConsole.setVisible(true);
                    displayTime.setGameIsRunning(false);
                }
            default:
                wall.player.playerStop();
        }
    }

    /**
     * keyReleased implements the method in KeyListener.
     * Stops the player's movement once key released.
     * @param keyEvent      to indicate if a key action has occurred or not.
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.playerStop();
    }

    /**
     * mouseClicked implements the method in MouseListener.
     * Implements the next course of actions if the CONTINUE, RESTART and EXIT buttons are clicked on.
     * @param mouseEvent    to indicate if a mouse action has occurred or not.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(pauseMenu.getContinueButtonRect().contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(pauseMenu.getRestartButtonRect().contains(p)){
            message = "Level "+level.getLevel()+"!  Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(pauseMenu.getExitButtonRect().contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * mouseMoved implements the method in MouseListener.
     * Implements what the cursor should look like when the cursor hovers over the CONTINUE, RESTART and EXIT buttons.
     * Implements what the cursor should look like otherwise.
     * @param mouseEvent       to indicate if a mouse action has occurred or not.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(pauseMenu.getExitButtonRect() != null && showPauseMenu) {
            if (pauseMenu.getExitButtonRect().contains(p) || pauseMenu.getContinueButtonRect().contains(p) || pauseMenu.getRestartButtonRect().contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * onLostFocus method implements what should occur when focus is lost.
     * Stops the game timer.
     * Prints out the message mentioning the focus is lost.
     * Redraw the screen.
     */
    public void onLostFocus(){
        gameTimer.stop();
        displayTime.setGameIsRunning(false);
        message = "Focus Lost";
        repaint();
    }

}
