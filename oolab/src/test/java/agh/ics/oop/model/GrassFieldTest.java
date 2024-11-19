package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void testGrassGeneration() {
        int numberOfGrass = 10;
        GrassField grassField = new GrassField(numberOfGrass);
        assertEquals(numberOfGrass, grassField.getGrassLocations().size());
    }

    @Test
    void testAnimalPlacement() {
        GrassField grassField = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));

        assertTrue(grassField.place(animal));
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void testIsOccupied() {
        GrassField grassField = new GrassField(10);

        Vector2d grassPosition = grassField.getGrassLocations().keySet().iterator().next();

        Animal animal = new Animal(new Vector2d(3, 3));
        grassField.place(animal);

        assertTrue(grassField.isOccupied(grassPosition));
        assertTrue(grassField.isOccupied(new Vector2d(3, 3)));

    }

    @Test
    void testAnimalMovement() {
        GrassField grassField = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);

        grassField.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());

        grassField.move(animal, MoveDirection.RIGHT);
        grassField.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(3, 3), animal.getPosition());
    }

    @Test
    void testCannotPlaceAnimalOnOccupiedPosition() {
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        assertTrue(grassField.place(animal1));
        assertFalse(grassField.place(animal2));
    }

    @Test
    void testMapVisualization() {
        GrassField grassField = new GrassField(5);
        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);

        String mapRepresentation = grassField.toString();
        assertNotNull(mapRepresentation);
        System.out.println(mapRepresentation);
    }
}