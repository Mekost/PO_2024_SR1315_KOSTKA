package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import agh.ics.oop.model.IncorrectPositionException;
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
        try {
            assertTrue(map.place(animal1));
            assertFalse(map.place(animal2));
            assertFalse(map.place(animal3));
            assertFalse(map.place(animal4));
            assertFalse(map.place(animal5));
            assertTrue(map.place(animal6));
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkingIfPlaceIsOccupied() {
        WorldMap map = new RectangularMap(10, 10);
        try {
            map.place(animal1);
            map.place(animal6);
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }
        assertTrue(map.isOccupied(animal1.getPosition()));
        assertTrue(map.isOccupied(animal6.getPosition()));
        assertFalse(map.isOccupied(new Vector2d(4, 7)));
    }

    @Test
    void movingAnimalOnMap() {
        WorldMap map = new RectangularMap(5, 5);
        Animal zwierz1 = new Animal();
        Animal zwierz2 = new Animal(new Vector2d(3, 4));
        try {
            map.place(zwierz1);
            map.place(zwierz2);
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }
        map.move(zwierz1, MoveDirection.FORWARD);
        map.move(zwierz1, MoveDirection.FORWARD);
        map.move(zwierz1, MoveDirection.RIGHT);
        map.move(zwierz1, MoveDirection.FORWARD);
        // po tym powinien być w (3, 4) ^ (gdyby nie było ograniczenia)
        assertEquals(zwierz1.getPosition(), new Vector2d(2, 4));
        map.move(zwierz1, MoveDirection.BACKWARD);
        map.move(zwierz1, MoveDirection.BACKWARD);
        map.move(zwierz1, MoveDirection.BACKWARD);
        assertEquals(zwierz1.getPosition(), new Vector2d(0, 4));
        map.move(zwierz2, MoveDirection.FORWARD);
        map.move(zwierz2, MoveDirection.FORWARD);
        assertEquals(zwierz2.getPosition(), new Vector2d(3,4));
    }

    @Test
    void checkingObjectAtPosition() {
        WorldMap map = new RectangularMap(10, 10);
        try {
            map.place(animal1);
            map.place(animal6);
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }
        assertEquals(map.objectAt(new Vector2d(2, 2)), animal1);
        assertEquals(map.objectAt(new Vector2d(4, 6)), animal6);
        assertNull(map.objectAt(new Vector2d(2,1)));
        assertNull(map.objectAt(new Vector2d(6,9)));
        assertNull(map.objectAt(new Vector2d(15, 16)));
    }

    @Test
    void canItMoveTo() throws IncorrectPositionException {
        RectangularMap map = new RectangularMap(10, 10);
        try {
            map.place(animal1);
            map.place(animal6);
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertTrue(map.canMoveTo(new Vector2d(5, 8)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        assertFalse(map.canMoveTo(new Vector2d(4, 6)));
    }

    @Test
    void testMapVisualization() throws IncorrectPositionException {
        RectangularMap map = new RectangularMap(10, 10);
        Animal animal = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }

        String mapRepresentation = map.toString();
        assertNotNull(mapRepresentation);
        //System.out.println(mapRepresentation);
    }

    @Test
    void testCurrentBounds() {
        RectangularMap map1 = new RectangularMap(10, 10);
        RectangularMap map2 = new RectangularMap(21, 37);
        assertEquals(new Boundary(new Vector2d(0, 0), new Vector2d(9, 9)), map1.getCurrentBounds());
        assertEquals(new Boundary(new Vector2d(0, 0), new Vector2d(20, 36)), map2.getCurrentBounds());

    }

}