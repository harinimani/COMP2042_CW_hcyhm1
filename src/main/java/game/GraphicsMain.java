
package game;

import model.GameFrameModel;

import java.awt.*;


public class GraphicsMain {

    public static void main(String[] args){
        EventQueue.invokeLater(() -> new GameFrameModel().initialize());
    }

}
