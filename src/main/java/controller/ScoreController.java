package controller;

import model.WallModel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreController {

    private static final String FILE = "src/main/resources/highScore_List.txt";
    BufferedReader reader = null;
    private String score[][] = new String[10][10];
    private String line = "";
    private String name, temp1,temp2,temp3,temp4;
    private static int i;
    private static int length;
    private WallModel wall;
    private GameTimeController gameTime;
    private static int limit;

    public ScoreController(WallModel wall) {
        this.wall = wall;
        setLength(0);
        gameTime = new GameTimeController();
        readFile();
        //System.out.println(getLength());
    }


    public void readFile(){

        try {
            reader = new BufferedReader(new FileReader(FILE));
            i=0;
            while ((line = reader.readLine()) != null) {

                if(line.length() > 0) {
                    //do lots of stuff to sort the data into lists etc
                    String[] row = line.split(",");

                    score[i][0] = row[0];
                    score[i][1] = row[1];
                    score[i][2] = row[2];
                    score[i][3] = row[3];
                    i++;
                    //length++;
                    setLength(i);
                }
            }//end of while
        } catch (Exception e) {
            System.err.println("An Error Occured!");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }//end of readFile

    public void writeFile(){
        try {
            FileWriter fileWriter = new FileWriter(FILE);
            //int limit;
            for(int k=0;k<6;k++){
                if(score[k][0] == null && score[k][1] == null && score[k][2]==null && score[k][3]==null)
                    break;
                fileWriter.write(score[k][0]+","+score[k][1]+","+score[k][2]+","+score[k][3]+"\n");
            }
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }//end of writeFile

    public void sortScores() {
        limit=0;
        if(getLength()<6)
            limit = getLength();
        else
            limit = 5;


        if(getLength()<6){
            playerNameDialogBox();
            score[getLength()][0] = name;
            score[getLength()][1] = String.valueOf(wall.getBrickBroken());
            score[getLength()][2] = String.valueOf(gameTime.getMinutes());
            score[getLength()][3] = String.valueOf(gameTime.getSeconds());
        }
        else {
            int num = Integer.parseInt(score[getLength()-1][1].trim());
            int min = Integer.parseInt(score[getLength()-1][2].trim());
            int sec = Integer.parseInt(score[getLength()-1][3].trim());
            if(num < wall.getBrickBroken()){
                playerNameDialogBox();
                score[5][0] = name;
                score[5][1] = String.valueOf(wall.getBrickBroken());
                score[5][2] = String.valueOf(gameTime.getMinutes());
                score[5][3] = String.valueOf(gameTime.getSeconds());
            }
            else if(num == wall.getBrickBroken() && min > gameTime.getMinutes()){
                playerNameDialogBox();
                score[5][0] = name;
                score[5][1] = String.valueOf(wall.getBrickBroken());
                score[5][2] = String.valueOf(gameTime.getMinutes());
                score[5][3] = String.valueOf(gameTime.getSeconds());
            }
            else if(num == wall.getBrickBroken() && min == gameTime.getMinutes() && sec > gameTime.getSeconds()){
                playerNameDialogBox();
                score[5][0] = name;
                score[5][1] = String.valueOf(wall.getBrickBroken());
                score[5][2] = String.valueOf(gameTime.getMinutes());
                score[5][3] = String.valueOf(gameTime.getSeconds());
            }


        }


        for(int i=1;i<(limit+1);i++) {
            for (int j = i + 1; j < (limit+1); j++) {
                //sorting to descending order
                int num1 = Integer.parseInt(score[i][1].trim());
                int num2 = Integer.parseInt(score[j][1].trim());
                if (num1 < num2) {
                    temp1 = score[i][0];
                    temp2 = score[i][1];
                    temp3 = score[i][2];
                    temp4 = score[i][3];

                    score[i][0] = score[j][0];
                    score[i][1] = score[j][1];
                    score[i][2] = score[j][2];
                    score[i][3] = score[j][3];

                    score[j][0] = temp1;
                    score[j][1] = temp2;
                    score[j][2] = temp3;
                    score[j][3] = temp4;
                }
            }
        }
        //if number of bricks broken same, but minutes taken differ
        for(int i=1;i<(limit+1);i++) {
            for (int j = i + 1; j < (limit+1); j++) {
                //sorting to descending order
                int num1 = Integer.parseInt(score[i][1].trim());
                int num2 = Integer.parseInt(score[j][1].trim());
                int num3 = Integer.parseInt(score[i][2].trim());
                int num4 = Integer.parseInt(score[j][2].trim());
                if ((num1 == num2) && (num3 > num4)) {
                    temp1 = score[i][0];
                    temp2 = score[i][1];
                    temp3 = score[i][2];
                    temp4 = score[i][3];

                    score[i][0] = score[j][0];
                    score[i][1] = score[j][1];
                    score[i][2] = score[j][2];
                    score[i][3] = score[j][3];

                    score[j][0] = temp1;
                    score[j][1] = temp2;
                    score[j][2] = temp3;
                    score[j][3] = temp4;
                }
            }
        }
        //if number of bricks broken and minutes taken same but seconds taken differ
        for(int i=1;i<(limit+1);i++) {
            for (int j = i + 1; j < (limit+1); j++) {
                //sorting to descending order
                int num1 = Integer.parseInt(score[i][1].trim());
                int num2 = Integer.parseInt(score[j][1].trim());
                int num3 = Integer.parseInt(score[i][2].trim());
                int num4 = Integer.parseInt(score[j][2].trim());
                int num5 = Integer.parseInt(score[i][3].trim());
                int num6 = Integer.parseInt(score[j][3].trim());
                if ((num1 == num2) && (num3 == num4) && (num5 > num6)) {
                    temp1 = score[i][0];
                    temp2 = score[i][1];
                    temp3 = score[i][2];
                    temp4 = score[i][3];

                    score[i][0] = score[j][0];
                    score[i][1] = score[j][1];
                    score[i][2] = score[j][2];
                    score[i][3] = score[j][3];

                    score[j][0] = temp1;
                    score[j][1] = temp2;
                    score[j][2] = temp3;
                    score[j][3] = temp4;
                }
            }
        }

        writeFile();
    }//end of sortScores method

    public String playerNameDialogBox(){
        name = JOptionPane.showInputDialog(null, "You set a new High-score! Enter your name:",
                "High-Score Player", JOptionPane.INFORMATION_MESSAGE);
        //System.out.println(name);
        //name =
        return name;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int length) {
        //this.length = length;
        if(length>=6)
            this.length = 6;
        else
            this.length = length;
    }

}
