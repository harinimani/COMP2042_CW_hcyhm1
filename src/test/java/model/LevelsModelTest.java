package model;

import controller.WallController;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LevelsModelTest {

    WallController wall = new WallController(new Rectangle(1, 1), new Point());
    LevelsModel level = new LevelsModel(new Rectangle(1, 1), 30, 3, 6 >> 2, wall);

    @Test
    void nextLevel() {
        wall.setBrickCount(5);
        assertEquals(5, wall.getBrickCount());
    }

    @Test
    void hasLevel() {
        assertTrue(level.hasLevel());
    }

    @Test
    void getLevel() {
        assertEquals(0, level.getLevel());
    }
}