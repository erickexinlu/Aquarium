package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Tank.MAX_FISH;
import static org.junit.jupiter.api.Assertions.*;

class FishTest {
    // delete or rename this class!

    Tank tank;
    Fish fish1;
    Fish fish2;

    @BeforeEach
    public void setup() {
        fish1 = new Fish("Dory","blue","Blue tang");
        fish2 = new Fish("Nemo","Orange","Goldfish");
        tank = new Tank();
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
    public void testStartTank() {
        assertTrue(tank.isEmpty());
    }

    @Test
    public void testAddRemoveFish() {
        tank.addFish(fish1);
        tank.addFish(fish2);
        assertEquals(tank.getFish(0), fish1);
        assertEquals(tank.getFish(1), fish2);
        tank.removeFish("Nemo");
        assertEquals(tank.size(), 1);
        assertEquals(tank.getFish(0), fish1);
    }



    @Test
    public void testIsFullAddFull() {
        for (int i = 0; i < MAX_FISH; i++) {
            tank.addFish(fish1);
        }
        assertTrue(tank.isFull());
        assertFalse(tank.addFish(fish2));
    }

    @Test
    public void testRemoveFalse() {
        assertFalse(tank.removeFish("Dory"));
        tank.addFish(fish1);
        assertFalse(tank.removeFish("Nemo"));
    }

    @Test
    public void testGetFishOutOfBounds() {
        tank.addFish(fish1);
        assertEquals(null, tank.getFish(1));
    }

    @Test
    public void testHungerAllFeedAll() {
        tank.addFish(fish1);
        tank.addFish(fish2);
        tank.hungerAllFish();
        assertEquals(fish1.getHunger(), 99);
        assertEquals(fish2.getHunger(), 99);
        tank.hungerAllFish();
        assertEquals(fish1.getHunger(), 98);
        assertEquals(fish2.getHunger(), 98);
        tank.feedAllFish();
        assertEquals(fish1.getHunger(), 100);
        assertEquals(fish2.getHunger(), 100);
    }

    @Test
    public void testGetSummary() {
        assertTrue(fish1.getSummary().equals("Dory is a blue Blue tang, with 100 hunger."));
    }





}