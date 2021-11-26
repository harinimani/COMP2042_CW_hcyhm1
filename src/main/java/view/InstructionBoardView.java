package view;

import javax.swing.*;
import java.awt.*;

public class InstructionBoardView {


    public InstructionBoardView() {
        JFrame frame = new JFrame("Instructions Menu");
        JPanel panel = new JPanel();

        LayoutManager overlay = new OverlayLayout(panel);
        panel.setLayout(overlay);

        JLabel label = new JLabel("<html>HOW TO PLAY:" +
                "<BR>"+
                "<BR>SPACE - Start or Pause the game" +
                "<BR>A Key - Moves the Player Left" +
                "<BR>D Key - Moves the Player Right"+
                "<BR>ESC Key - Enter or Exit the Pause Menu"+
                "<BR>ALT+SHIFT+F1 - Open Debug Console</html>",JLabel.CENTER);

        label.setFont(new Font("Courier", Font.BOLD,24));

        label.setAlignmentX(0.5f);
        label.setAlignmentY(0.5f);

        panel.add(label);
        label.setForeground(Color.WHITE);


        JLabel backgroundImg = new JLabel(new ImageIcon("src/main/resources/scaled_blue_brick_wall.png"));
        backgroundImg.setAlignmentX(0.5f);
        backgroundImg.setAlignmentY(0.5f);
        panel.add(backgroundImg);

        frame.add(panel);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
