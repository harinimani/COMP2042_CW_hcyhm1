
package game;

import controller.GameFrameController;

import java.awt.*;


/**
 * GraphicsMain class to run and play the game.
 */
public class GraphicsMain {

    public static void main(String[] args){
        EventQueue.invokeLater(() -> new GameFrameController().initialize());
    }

}
