package controller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * GameTimeController class is responsible for handling and keeping track of the in-game time.
 *
 * @author Harini Manikandan
 */
public class GameTimeController {

    private boolean gameIsRunning;
    private static int seconds;
    private static int minutes;


    /**
     * GameTimeController() is a Default Constructor that keeps track of the in-game time taken.
     * Uses a Timer to increment the seconds and minutes.
     */
    public GameTimeController() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(isGameRunning())
                    setSeconds(getSeconds() + 1);
                if(getSeconds()>59){
                    setSeconds(0);
                    setMinutes(getMinutes() + 1);
                }
            }
        },0,1000);
    }


    /**
     * Getter method for the seconds variable.
     * @return      returns the seconds value
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Setter method for setting the seconds.
     * @param seconds   seconds value
     */
    public void setSeconds(int seconds) {
        GameTimeController.seconds = seconds;
    }

    /**
     * Getter method for the minutes variable.
     * @return      returns the minutes value
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Setter method for setting the minutes.
     * @param minutes   minutes value
     */
    public void setMinutes(int minutes) {
        GameTimeController.minutes = minutes;
    }

    /**
     * Setter method to store the boolean value of the gameIsRunning variable.
     * @param gameIsRunning     boolean value to notify is game is running or not
     */
    public void setGameIsRunning(boolean gameIsRunning) {
        this.gameIsRunning = gameIsRunning;
    }

    /**
     * Getter method to access the value of gameIsRunning variable.
     * @return      returns a boolean value to notify if game is running or not
     */
    public boolean isGameRunning() {
        return gameIsRunning;
    }

}
