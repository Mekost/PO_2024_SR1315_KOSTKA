package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    Animal animal1 = new Animal();
    Animal animal2 = new Animal();
    Animal animal3 = new Animal(new Vector2d(15, 20));
    Animal animal4 = new Animal(new Vector2d(-5, 14));
    Animal animal5 = new Animal(new Vector2d(-42, -4125));
    Animal animal6 = new Animal(new Vector2d(4,6));

    @Test
    void placingAnimalOnMap() {
        WorldMap map = new RectangularMap(10, 10);
        assertTrue(map.place(animal1));
        assertFalse(map.place(animal2));
        assertFalse(map.place(animal3));
        assertFalse(map.place(animal4));
        assertFalse(map.place(animal5));
        assertTrue(map.place(animal6));
    }

    @Test
    void checkingIfPlaceIsOccupied() {
        WorldMap map = new RectangularMap(10, 10);
        map.place(animal1);
        map.place(animal6);
        assertTrue(map.isOccupied(animal1.getLocation()));
        assertTrue(map.isOccupied(animal6.getLocation()));
        assertFalse(map.isOccupied(new Vector2d(4, 7)));
    }

    @Test
    void movingAnimalOnMap() {
        WorldMap map = new RectangularMap(5, 5);
        Animal zwierz1 = new Animal();
        Animal zwierz2 = new Animal(new Vector2d(3, 4));
        map.place(zwierz1);
        map.place(zwierz2);
        map.move(zwierz1, MoveDirection.FORWARD);
        map.move(zwierz1, MoveDirection.FORWARD);
        map.move(zwierz1, MoveDirection.RIGHT);
        map.move(zwierz1, MoveDirection.FORWARD);
        // po tym powinien być w (3, 4) ^ (gdyby nie było ograniczenia)
        assertEquals(zwierz1.getLocation(), new Vector2d(2, 4));
        map.move(zwierz1, MoveDirection.BACKWARD);
        map.move(zwierz1, MoveDirection.BACKWARD);
        map.move(zwierz1, MoveDirection.BACKWARD);
        assertEquals(zwierz1.getLocation(), new Vector2d(0, 4));
        map.move(zwierz2, MoveDirection.FORWARD);
        map.move(zwierz2, MoveDirection.FORWARD);
        assertEquals(zwierz2.getLocation(), new Vector2d(3,5));
    }

    @Test
    void checkingObjectAtPosition() {
        WorldMap map = new RectangularMap(10, 10);
        map.place(animal1);
        map.place(animal6);
        assertEquals(map.objectAt(new Vector2d(2, 2)), animal1);
        assertEquals(map.objectAt(new Vector2d(4, 6)), animal6);
        assertNull(map.objectAt(new Vector2d(2,1)));
        assertNull(map.objectAt(new Vector2d(6,9)));
        assertNull(map.objectAt(new Vector2d(15, 16)));
    }

    @Test
    void canItMoveTo() {
        RectangularMap map = new RectangularMap(10, 10);
        map.place(animal1);
        map.place(animal6);
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertTrue(map.canMoveTo(new Vector2d(5, 8)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        assertFalse(map.canMoveTo(new Vector2d(4, 6)));
    }


}