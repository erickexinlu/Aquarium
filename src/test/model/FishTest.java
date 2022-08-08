package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Tank.*;
import static org.junit.jupiter.api.Assertions.*;

class FishTest {
    // delete or rename this class!

    Tank tank;
    Fish fish1;
    Fish fish2;
    Fish leftFish, topFish, rightFish, bottomFish, centerFish;

    @BeforeEach
    public void setup() {
        fish1 = new Fish("Dory","blue","Blue tang");
        fish2 = new Fish("Nemo","Orange","Goldfish");
        tank = new Tank();
        leftFish = new Fish(2, 300, -5, 0);
        topFish = new Fish(300, 2, 0, -5);
        rightFish = new Fish(598, 300, 5, 0);
        bottomFish = new Fish(300, 398, 0, 5);
        centerFish = new Fish(300, 300, 3, 3);

    }

    @Test
    public void testMove() {
        centerFish.move();
        assertEquals(centerFish.getPosX(), 303);
        assertEquals(centerFish.getPosY(), 303);
    }

    @Test
    public void testBoundaries() {
        leftFish.move();
        assertEquals(leftFish.getPosX(), 13);
        rightFish.move();
        assertEquals(rightFish.getPosX(), TANK_WIDTH - 13);
        topFish.move();
        assertEquals(topFish.getPosY(), 13);
        bottomFish.move();
        assertEquals(bottomFish.getPosY(), TANK_HEIGHT - 13);
    }

    @Test
    public void testFish() {
        assertEquals(fish1.getName(), "Dory");
        assertEquals(fish2.getColor(), "Orange");
        assertEquals(fish2.getSpecies(), "Goldfish");
    }

    @Test
    public void testDecreaseHunger() {
        assertEquals(fish1.getHunger(), 100);
        fish1.decreaseHunger();
        assertEquals(fish1.getHunger(), 99);
        fish1.decreaseHunger();
        assertEquals(fish1.getHunger(), 98);
        fish2.decreaseHunger();
        assertEquals(fish2.getHunger(), 99);
    }

    @Test
    public void testFeed() {
        fish1.decreaseHunger();
        fish1.decreaseHunger();
        assertEquals(fish1.getHunger(), 98);
        fish1.feed();
        assertEquals(fish1.getHunger(), 100);
    }

    @Test
    public void testGetFishOutOfBounds() {
        tank.addFish(fish1);
        assertEquals(null, tank.getFish(1));
    }

    @Test
    public void testGetSummary() {
        assertTrue(fish1.getSummary().equals("Dory is a blue Blue tang, with 100 hunger."));
    }







}