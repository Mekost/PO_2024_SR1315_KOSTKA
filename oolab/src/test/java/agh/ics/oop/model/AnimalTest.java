package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Animal animal1 = new Animal();
    Animal animal2 = new Animal(new Vector2d(2, 3));
    Animal animal3 = new Animal(new Vector2d(4, 1));
    WorldMap map =  new RectangularMap(4, 4);

    @Test
    void convertingToString() {
        assertEquals("N", animal1.toString());
        assertEquals("N", animal2.toString());
        assertEquals("N", animal3.toString());
    }

    @Test
    void checkingEqualityOfPositions() {
        assertTrue(animal1.isAt(animal1.getLocation()));
        assertFalse(animal2.isAt(animal3.getLocation()));
        assertTrue(animal3.isAt(new Vector2d(4, 1)));
    }

    @Test
    void checkingNorthBoundaryWhenGoingForward() {
        Animal test = new Animal(new Vector2d(0, 4));
        map.move(test, MoveDirection.FORWARD);
        // w wyniku takiej operacji powinien zmienić współrzędne na (0,5) gdyby nie było ograniczeń
        assertEquals(test.getLocation(), new Vector2d(0, 4));
    }

    @Test
    void checkingSouthBoundaryWhenGoingForward() {
        Animal test = new Animal(new Vector2d(0, 0));
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.FORWARD);
        assertEquals(test.getLocation(), new Vector2d(0, 0));
    }

    @Test
    void checkingWestBoundaryWhenGoingForward() {
        Animal test = new Animal(new Vector2d(0, 0));
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.FORWARD);
        assertEquals(test.getLocation(), new Vector2d(0, 0));
    }

    @Test
    void checkingEastBoundaryWhenGoingForward() {
        Animal test = new Animal(new Vector2d(3, 0));
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.FORWARD);
        assertEquals(test.getLocation(), new Vector2d(4, 0));
        map.move(test, MoveDirection.FORWARD);
        assertEquals(test.getLocation(), new Vector2d(4, 0));
    }

    @Test
    void checkingNorthBoundaryWhenGoingBackward() {
        Animal test = new Animal(new Vector2d(0, 4));
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.BACKWARD);
        // w wyniku takiej operacji powinien zmienić współrzędne na (0,5) gdyby nie było ograniczeń
        assertEquals(test.getLocation(), new Vector2d(0, 4));
    }

    @Test
    void checkingSouthBoundaryWhenGoingBackward() {
        Animal test = new Animal(new Vector2d(0, 0));
        map.move(test, MoveDirection.BACKWARD);
        assertEquals(test.getLocation(), new Vector2d(0, 0));
    }

    @Test
    void checkingWestBoundaryWhenGoingBackward() {
        Animal test = new Animal(new Vector2d(0, 0));
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.BACKWARD);
        assertEquals(test.getLocation(), new Vector2d(0, 0));
    }

    @Test
    void checkingEastBoundaryWhenGoingBackward() {
        Animal test = new Animal(new Vector2d(4, 0));
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.RIGHT);
        map.move(test, MoveDirection.BACKWARD);
        assertEquals(test.getLocation(), new Vector2d(4, 0));
    }

    @Test
    void checkingRightMethod() {
        map.move(animal1, MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, animal1.getOrientation());
        map.move(animal1, MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, animal1.getOrientation());
        map.move(animal1, MoveDirection.RIGHT);
        assertEquals(MapDirection.WEST, animal1.getOrientation());
        map.move(animal1, MoveDirection.RIGHT);
        assertEquals(MapDirection.NORTH, animal1.getOrientation());
    }

    @Test
    void checkingLeftMethod() {
        map.move(animal1, MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, animal1.getOrientation());
        map.move(animal1, MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, animal1.getOrientation());
        map.move(animal1, MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, animal1.getOrientation());
        map.move(animal1, MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, animal1.getOrientation());
    }

    @Test
    void orientationGetter() {
        assertEquals(MapDirection.NORTH, animal1.getOrientation());
        map.move(animal1, MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, animal1.getOrientation());
    }

    @Test
    void locationGetter() {
        assertEquals(new Vector2d(2, 2), animal1.getLocation());
        map.move(animal1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal1.getLocation());
    }
}