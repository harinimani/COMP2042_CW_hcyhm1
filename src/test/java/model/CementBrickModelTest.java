package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickModelTest {

    CementBrickModel cementBrick = new CementBrickModel(new Point(), new Dimension(1,1));

    @Test
    void makeBrickFace() {
        assertEquals(new Rectangle(new Point(), new Dimension(1,1)),
                cementBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }

    @Test
    void setImpact() {
        cementBrick.impact();
        assertEquals(1,cementBrick.getStrength());
    }

    @Test
    void getBrick() {
        assertEquals(cementBrick.getBrick(),
                cementBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }

    @Test
    void repair() {
        cementBrick.impact();
        cementBrick.repair();
        assertEquals(2,cementBrick.getStrength());
    }
}