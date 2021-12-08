package view;

import java.awt.*;
import java.awt.font.FontRenderContext;

public class PauseMenuView {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";

    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;

    private GameBoardView gameBoard;


    private int strLen;

    private Font menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);;


    public void PauseMenuScreen(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * obscureGameBoard is a private Method that draws the Pause Menu container screen and sets its properties.
     * Draws on top of the existing In-Game Screen.
     * Handles the blending and transparency of the screen.
     * @param g2d
     */
    public void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);
        //specifies how new pixels are to be combined with the existing pixels on the graphics device.
        //blending & transparency

        g2d.setColor(Color.BLACK);  //pause menu bg color
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * drawPauseMenu is a private Method that draws and renders the fonts on the Pause Menu Screen.
     * Draws the CONTINUE, RESTART and EXIT buttons.
     * @param g2d
     */
    public void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        //Pause Menu Font
        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (DEF_WIDTH - strLen) / 2;
        int y = DEF_HEIGHT / 10;

        g2d.drawString(PAUSE,x,y);

        x = DEF_WIDTH / 8;
        y = DEF_HEIGHT / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    public Rectangle getContinueButtonRect() {
        return continueButtonRect;
    }

    public Rectangle getExitButtonRect() {
        return exitButtonRect;
    }

    public Rectangle getRestartButtonRect() {
        return restartButtonRect;
    }

    public void setStrLen(int strLen) {
        this.strLen = strLen;
    }

}
