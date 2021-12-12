package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SlowBrickModelTest {

    SlowBrickModel slowBrick = new SlowBrickModel(new Point(), new Dimension(1,1));

    @Test
    void makeBrickFace() {
        assertEquals(new Rectangle(new Point(), new Dimension(1,1)),
                slowBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }

    @Test
    void setImpact() {
        slowBrick.impact();
        assertEquals(0,slowBrick.getStrength());
    }

    @Test
    void getBrick() {
        assertEquals(slowBrick.getBrick(),
                slowBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }
}