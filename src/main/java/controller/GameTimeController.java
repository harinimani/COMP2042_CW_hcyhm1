package controller;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimeController {

    private boolean gameIsRunning;
    private static int seconds;
    private static int minutes;


    public GameTimeController() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(gameIsRunning)
                    setSeconds(getSeconds() + 1);
                if(getSeconds()>59){
                    setSeconds(0);
                    setMinutes(getMinutes() + 1);
                }
            }
        },0,1000);
    }


    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        GameTimeController.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        GameTimeController.minutes = minutes;
    }

    public void setGameIsRunning(boolean gameIsRunning) {
        this.gameIsRunning = gameIsRunning;
    }

}
