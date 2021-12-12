package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {

    PlayerModel player = new PlayerModel(new Point(201,415), 1,1,new Rectangle());


    @Test
    void playerMoveLeft() {
        PlayerModel.setDefMoveAmount(5);
        assertEquals(-5,-player.getDefMoveAmount());
    }

    @Test
    void playerMoveRight() {
        PlayerModel.setDefMoveAmount(5);
        assertEquals(5,player.getDefMoveAmount());
    }

    @Test
    void playerStop() {
        PlayerModel.setDefMoveAmount(0);
        assertEquals(0,player.getDefMoveAmount());
    }

    @Test
    void getPlayerFace() {
        assertEquals(new Rectangle(new Point(201,415), new Dimension(1,1)),
                player.makePlayer(1,1));
    }


    @Test
    void setInnerColor() {
        PlayerModel.setInnerColor(Color.GREEN);
        assertEquals(Color.GREEN, player.getInnerColor());
    }

    @Test
    void getInnerColor() {
        PlayerModel.setInnerColor(Color.GREEN);
        assertEquals(Color.GREEN, player.getInnerColor());
    }

    @Test
    void getDefMoveAmount() {
        PlayerModel.setDefMoveAmount(10);
        assertEquals(10, player.getDefMoveAmount());
    }

    @Test
    void setDefMoveAmount() {
        PlayerModel.setDefMoveAmount(10);
        assertEquals(10, player.getDefMoveAmount());
    }
}