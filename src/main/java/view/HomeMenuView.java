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
package view;

import controller.GameFrameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * HomeMenuView class is the class for the implementation of the starting page when the game begins.
 * Displays the Game Title and the Start and Exit buttons.
 */
public class HomeMenuView extends JPanel implements ActionListener {

    private static final String START_BUTTON_BG = "src/main/resources/ButtonBg.png";
    private static final String INFO_BUTTON_BG = "src/main/resources/InfoButtonBg.png";
    private static final String EXIT_BUTTON_BG = "src/main/resources/ExitButtonBg.png";
    private static final String SCORES_BUTTON_BG = "src/main/resources/ScoresButtonBg.png";
    private static final String HOME_MENU_BG = "src/main/resources/HomeMenuBg.png";

    private Image backgroundImage;
    private GameFrameController owner;
    private JButton start;
    private JButton info;
    private JButton exit;
    private JButton scores;


    public HomeMenuView(GameFrameController owner) {
        this.owner = owner;

        this.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        ImageIcon startIcon = new ImageIcon(new ImageIcon(START_BUTTON_BG).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        ImageIcon infoIcon = new ImageIcon(new ImageIcon(INFO_BUTTON_BG).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        ImageIcon exitIcon = new ImageIcon(new ImageIcon(EXIT_BUTTON_BG).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        ImageIcon scoresIcon = new ImageIcon(new ImageIcon(SCORES_BUTTON_BG).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

        start = new JButton("START");
        info = new JButton("INFO");
        exit = new JButton("EXIT");
        scores = new JButton("SCORES");

        try {
            BufferedImage background = ImageIO.read(new File(HOME_MENU_BG));
            backgroundImage = background;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setLayout(new GridBagLayout());

        //GBConstraints = new GridBagConstraints();

        //Start Button
        setMyConstraints(c,0,2,20,25,GridBagConstraints.LAST_LINE_START);
        c.insets = new Insets(5,5,5,5);
        c.weightx = 0.5;
        c.weighty = 0.5;
        start.setPreferredSize(new Dimension(104, 25));
        start.setFont(new Font("Arial", Font.BOLD, 25));
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.setHorizontalTextPosition(JButton.CENTER);
        start.setVerticalTextPosition(JButton.CENTER);
        start.setIcon(startIcon);
        add(start,c);
        start.addActionListener(e -> owner.enableGameBoard());

        //Info Button
        setMyConstraints(c,1,2,25,25,GridBagConstraints.PAGE_END);
        info.setPreferredSize(new Dimension(100, 25));
        info.setFont(new Font("Arial", Font.BOLD, 28));
        info.setForeground(Color.WHITE);
        info.setFocusPainted(false);
        info.setHorizontalTextPosition(JButton.CENTER);
        info.setVerticalTextPosition(JButton.CENTER);
        info.setIcon(infoIcon);
        add(info,c);
        info.addActionListener(e -> owner.enableInfoBoard());


        //Exit Button
        setMyConstraints(c,2,2,25,25,GridBagConstraints.LAST_LINE_END);
        exit.setPreferredSize(new Dimension(100, 25));
        exit.setFont(new Font("Arial", Font.BOLD, 28));
        exit.setForeground(Color.WHITE);
        exit.setFocusPainted(false);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);
        exit.setIcon(exitIcon);
        add(exit,c);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Goodbye " + System.getProperty("user.name"));
                System.exit(0);
            }
        });


        //LEADERBOARD button
        setMyConstraints(c,3,2,25,25,GridBagConstraints.LAST_LINE_END);
        scores.setPreferredSize(new Dimension(100, 25));
        scores.setFont(new Font("Arial", Font.BOLD, 20));
        scores.setForeground(Color.WHITE);
        scores.setFocusPainted(false);
        scores.setHorizontalTextPosition(JButton.CENTER);
        scores.setVerticalTextPosition(JButton.CENTER);
        scores.setIcon(scoresIcon);
        add(scores,c);
        scores.addActionListener(e -> owner.enableLeaderboard());

    }

    @Override
    public Dimension getPreferredSize() {
        return backgroundImage == null ? super.getPreferredSize() : new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (getWidth() - backgroundImage.getWidth(this)) / 2;
        int y = (getHeight() - backgroundImage.getHeight(this)) / 2;
        g.drawImage(backgroundImage, x, y, this);
    }

    @Override
    public void actionPerformed(ActionEvent l) {


    }

    private static void setMyConstraints(GridBagConstraints c, int gridx, int gridy, int ipadx, int ipady, int anchor) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.ipadx = ipadx;
        c.ipady = ipady;
        c.anchor = anchor;
    }

}
