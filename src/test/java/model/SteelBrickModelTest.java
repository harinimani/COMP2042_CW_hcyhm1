package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickModelTest {

    SteelBrickModel steelBrick = new SteelBrickModel(new Point(), new Dimension(1,1));

    @Test
    void makeBrickFace() {
        assertEquals(new Rectangle(new Point(), new Dimension(1,1)),
                steelBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }

    @Test
    void getBrick() {
        assertEquals(steelBrick.getBrick(),
                steelBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }

    @Test
    void setImpact() {
        steelBrick.impact();
        assertEquals(0,steelBrick.getStrength());
    }

    @Test
    void impact() {

    }
}