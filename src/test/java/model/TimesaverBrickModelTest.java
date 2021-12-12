package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TimesaverBrickModelTest {

    TimesaverBrickModel timeBrick = new TimesaverBrickModel(new Point(), new Dimension(1,1));

    @Test
    void makeBrickFace() {
        assertEquals(new Rectangle(new Point(), new Dimension(1,1)),
                timeBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }

    @Test
    void setImpact() {
        timeBrick.impact();
        assertEquals(0,timeBrick.getStrength());
    }

    @Test
    void getBrick() {
        assertEquals(timeBrick.getBrick(),
                timeBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }
}