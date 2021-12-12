package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BonusBrickModelTest {

    BonusBrickModel bonusBrick = new BonusBrickModel(new Point(), new Dimension(1,1));

    @Test
    void makeBrickFace() {
        assertEquals(new Rectangle(new Point(), new Dimension(1,1)),
                bonusBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }

    @Test
    void setImpact() {
        bonusBrick.impact();
        assertEquals(0,bonusBrick.getStrength());
    }

    @Test
    void getBrick() {
        assertEquals(bonusBrick.getBrick(),
                bonusBrick.makeBrickFace(new Point(), new Dimension(1,1)));
    }
}