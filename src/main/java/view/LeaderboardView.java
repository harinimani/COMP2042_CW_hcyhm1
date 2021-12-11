package view;

import model.GameFrameModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class LeaderboardView extends JPanel implements ActionListener {

    private static final String BACK_BUTTON = "src/main/resources/ButtonBg.png";
    private static final String LEADERBOARD_BG = "src/main/resources/LEADERBOARD.png";

    private Image backgroundImage;
    private GameFrameModel owner;
    private JButton backButton;
    private ScoresTableView scoresTable;

    public LeaderboardView(GameFrameModel owner) {
        this.owner = owner;

        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints t = new GridBagConstraints();
        ImageIcon buttonBg = new ImageIcon(new ImageIcon(BACK_BUTTON).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));


        backButton = new JButton("BACK");

        try {
            BufferedImage leaderboardBg = ImageIO.read(new File(LEADERBOARD_BG));
            backgroundImage = leaderboardBg;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setLayout(new GridBagLayout());

        //BACK Button
        setMyConstraints(c,0,2,20,25,GridBagConstraints.PAGE_END);
        c.insets = new Insets(5,5,5,5);
        c.weightx = 0.5;
        c.weighty = 0.5;
        backButton.setPreferredSize(new Dimension(104, 25));
        backButton.setFont(new Font("Arial", Font.BOLD, 25));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setHorizontalTextPosition(JButton.CENTER);
        backButton.setVerticalTextPosition(JButton.CENTER);
        backButton.setIcon(buttonBg);
        add(backButton,c);
        backButton.addActionListener(e -> owner.enableHomeMenuboard());

        //Scores Table
        scoresTable = new ScoresTableView();
        setMyConstraints(t,0,1,0,0,GridBagConstraints.CENTER);
        //t.insets = new Insets(5,5,5,5);
        t.weightx = 0.5;
        t.weighty = 0.5;
        scoresTable.setLocation(250,400);
        add(scoresTable,t);


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

    private static void setMyConstraints(GridBagConstraints c, int gridx, int gridy, int ipadx, int ipady, int anchor) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.ipadx = ipadx;
        c.ipady = ipady;
        c.anchor = anchor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
