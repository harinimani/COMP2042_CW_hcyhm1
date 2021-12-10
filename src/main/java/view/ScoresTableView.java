package view;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScoresTableView extends JPanel {

    private BufferedReader reader;
    private String score[][];
    private String data[][];
    private int i,k,length;
    private String line ="";

    public ScoresTableView() {
        score = new String[6][6];
        data = new String[6][6];
        i=0;
        length=0;

        GridBagConstraints c = new GridBagConstraints();
        //this.setLayout(new BorderLayout());
        setLayout(new GridBagLayout());

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/highScore_List.txt"));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                score[i][0] = row[0];
                score[i][1] = row[1];
                score[i][2] = row[2];
                score[i][3] = row[3];
                i++;
                length++;
            }//end of while
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String column[]={"NAME","BRICKS BROKEN","MINS","SEC"};
        k=0;
        for(i=1; i<(length+1); i++){
            //k=0;
            for(int j=0; j<4; j++){
                data[k][j] = score[i][j];
            }
            k++;
        }


        setMyConstraints(c,0,0,2,2, GridBagConstraints.CENTER);
        c.insets = new Insets(5,5,5,5);
        c.weightx = 0.5;
        c.weighty = 0.5;
        JTable jt=new JTable(data,column);
        jt.setPreferredScrollableViewportSize(new Dimension(450,100));
        jt.setFillsViewportHeight(true);
        //jt.setBounds(50,200,100,80);
        JScrollPane sp=new JScrollPane(jt);
        sp.setViewportView(jt);
        //sp.setLocation(80,500);
        //sp.setPreferredSize(20,30);
        //jt.setFillsViewportHeight(true);
        add(sp,c);
    }

    private static void setMyConstraints(GridBagConstraints c, int gridx, int gridy, int ipadx, int ipady, int anchor) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.ipadx = ipadx;
        c.ipady = ipady;
        c.anchor = anchor;
    }

}
