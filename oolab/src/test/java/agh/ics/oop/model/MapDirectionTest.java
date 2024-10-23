package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {

    MapDirection mapDirection = MapDirection.SOUTH;
    MapDirection mapDirection2 = MapDirection.NORTH;
    MapDirection mapDirection3 = MapDirection.WEST;
    MapDirection mapDirection4 = MapDirection.EAST;
    @Test
    void isNextMethodWorkingWell() {
        assertEquals(MapDirection.WEST, mapDirection.next());
        assertEquals(MapDirection.EAST, mapDirection2.next());
        assertEquals(MapDirection.NORTH, mapDirection3.next());
        assertEquals(MapDirection.SOUTH, mapDirection4.next());
    }
    @Test
    void isPreviousMethodWorkingWell() {
        assertEquals(MapDirection.EAST, mapDirection.previous());
        assertEquals(MapDirection.WEST, mapDirection2.previous());
        assertEquals(MapDirection.SOUTH, mapDirection3.previous());
        assertEquals(MapDirection.NORTH, mapDirection4.previous());
    }


}
