package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickModelTest {

    ClayBrickModel clayBrick = new ClayBrickModel(new Point(), new Dimension(1,1));

    @Test
    void makeBrickFace() {
        assertEquals(new Rectangle(new Point(), new Dimension(1,1)),
                clayBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }

    @Test
    void setImpact() {
        clayBrick.impact();
        assertEquals(0,clayBrick.getStrength());
    }

    @Test
    void getBrick() {
        assertEquals(clayBrick.getBrick(),
                clayBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }
}