package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Tank.MAX_FISH;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TankTest {

    Tank tank;
    Fish fish1;
    Fish fish2;
    Fish centerFish1;
    Fish centerFish2;

    @BeforeEach
    public void setup() {
        fish1 = new Fish("Dory","blue","Blue tang");
        fish2 = new Fish("Nemo","Orange","Goldfish");
        tank = new Tank();

        centerFish1 = new Fish(300, 300, 3, 3);
        centerFish2 = new Fish(300, 300, 5, 5);
    }

    @Test
    public void testUpdate() {
        tank.addFish(centerFish1);
        tank.addFish(centerFish2);
        tank.update();

        assertEquals(centerFish1.getPosX(), 303);
        assertEquals(centerFish1.getPosY(), 303);
        assertEquals(centerFish2.getPosY(), 305);

        tank.hungerAllFish();
        assertEquals(centerFish1.getHunger(), 99);
        assertEquals(centerFish2.getHunger(), 99);
    }

    @Test
    public void testStartTank() {
        assertTrue(tank.isEmpty());
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
    public void testAddRemoveFish() {
        tank.addFish(fish1);
        assertFalse(tank.isEmpty());
        tank.addFish(fish2);
        assertEquals(tank.getFish(0), fish1);
        assertEquals(tank.getFish(1), fish2);
        tank.removeFish("Nemo");
        assertEquals(tank.size(), 1);
        assertEquals(tank.getFish(0), fish1);
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
}
