package controller;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallControllerTest {

    WallController wall = new WallController(new Rectangle(), new Point());

    @Test
    void getBrickCount() {
        wall.setBrickCount(10);
        assertEquals(10,wall.getBrickCount());
    }

    @Test
    void getBallCount() {
        wall.setBallCount(2);
        assertEquals(2,wall.getBallCount());
    }

    @Test
    void setBallCount() {
        wall.setBallCount(1);
        assertEquals(1,wall.getBallCount());
    }

    @Test
    void isBallLost() {
        wall.setBallLost(true);
        assertTrue(wall.isBallLost());
    }

    @Test
    void ballReset() {
        wall.setBallLost(true);
        wall.ballReset();
        assertFalse(wall.isBallLost());
    }

    @Test
    void ballEnd() {
        wall.setBallCount(0);
        assertTrue(wall.getBallCount() == 0);
        wall.setBallCount(3);
        assertFalse(wall.getBallCount() == 0);
    }

    @Test
    void isDone() {
        wall.setBrickCount(5);
        assertFalse(wall.getBrickCount()==0);
        wall.setBrickCount(0);
        assertTrue(wall.getBrickCount()==0);
    }

    @Test
    void resetBallCount() {
        wall.setBallCount(1);
        wall.resetBallCount();
        assertEquals(3,wall.getBallCount());
    }

    @Test
    void getBrickBroken() {
        WallController.setBrickBroken(10);
        assertEquals(10, WallController.getBrickBroken());
    }

    @Test
    void setBrickBroken() {
        WallController.setBrickBroken(10);
        assertEquals(10, WallController.getBrickBroken());
    }

}