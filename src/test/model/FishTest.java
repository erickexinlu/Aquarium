package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.TankApp;

import static org.junit.jupiter.api.Assertions.*;
import static ui.TankApp.MAX_FISH;

class FishTest {
    // delete or rename this class!

    TankApp tankApp;
    Fish fish1;
    Fish fish2;

    @BeforeEach
    public void setup() {
        fish1 = new Fish("Dory","blue","Blue tang");
        fish2 = new Fish("Nemo","Orange","Goldfish");
        tankApp = new TankApp();
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
        assertTrue(tankApp.isEmpty());
    }

    @Test
    public void testAddRemoveFish() {
        tankApp.addFish(fish1);
        tankApp.addFish(fish2);
        assertEquals(tankApp.getFish(0), fish1);
        assertEquals(tankApp.getFish(1), fish2);
        tankApp.removeFish("Nemo");
        assertEquals(tankApp.size(), 1);
        assertEquals(tankApp.getFishFromName("Dory"), fish1);
    }

    @Test
    public void testIsFull() {
        for (int i = 0; i < MAX_FISH; i++) {
            tankApp.addFish(fish1);
        }
        assertTrue(tankApp.isFull());
    }

    @Test
    public void testHungerAllFeedAll() {
        tankApp.addFish(fish1);
        tankApp.addFish(fish2);
        tankApp.hungerAllFish();
        assertEquals(fish1.getHunger(), 99);
        assertEquals(fish2.getHunger(), 99);
        tankApp.hungerAllFish();
        assertEquals(fish1.getHunger(), 98);
        assertEquals(fish2.getHunger(), 98);
        tankApp.feedAllFish();
        assertEquals(fish1.getHunger(), 100);
        assertEquals(fish2.getHunger(), 100);
    }

    @Test
    public void testGetSummary() {
        assertEquals(fish1.getSummary(), "Dory is a blue Blue Tang.");
    }






}