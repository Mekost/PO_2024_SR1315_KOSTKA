package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RandomPositionGeneratorTest {

    @Test
    void checkingSizeOfList() {
        RandomPositionGenerator generator1 = new RandomPositionGenerator(5, 10, 3);
        RandomPositionGenerator generator2 = new RandomPositionGenerator(4, 7, 4);
        RandomPositionGenerator generator3 = new RandomPositionGenerator(8,7,6);
        assertEquals(generator1.generatingPossibilities().size(), 50);
        assertEquals(generator2.generatingPossibilities().size(), 28);
        assertEquals(generator3.generatingPossibilities().size(), 56);
    }

    @Test
    void grassCountNumber() {
        RandomPositionGenerator generator1 = new RandomPositionGenerator(5, 10, 3);
        RandomPositionGenerator generator2 = new RandomPositionGenerator(5,6,40);
        assertEquals(generator1.getGrassCount(), 3);
        assertEquals(generator2.getGrassCount(), 30);
    }

}