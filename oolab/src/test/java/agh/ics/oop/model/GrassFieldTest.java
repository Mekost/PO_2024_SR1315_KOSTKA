package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void testGrassGeneration() {
        int numberOfGrass = 10;
        GrassField grassField = new GrassField(numberOfGrass);
        assertEquals(numberOfGrass, grassField.getGrassLocations().size());
    }

    @Test
    void testAnimalPlacement() throws IncorrectPositionException {
        try {
            GrassField grassField = new GrassField(10);
            Animal animal = new Animal(new Vector2d(2, 2));

            assertTrue(grassField.place(animal));
            assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)));
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testIsOccupied() throws IncorrectPositionException {
        try {
            GrassField grassField = new GrassField(10);

            Vector2d grassPosition = grassField.getGrassLocations().keySet().iterator().next();

            Animal animal = new Animal(new Vector2d(3, 3));
            grassField.place(animal);

            assertTrue(grassField.isOccupied(grassPosition));
            assertTrue(grassField.isOccupied(new Vector2d(3, 3)));
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testAnimalMovement() throws IncorrectPositionException {
        try {
            GrassField grassField = new GrassField(10);
            Animal animal = new Animal(new Vector2d(2, 2));
            grassField.place(animal);

            grassField.move(animal, MoveDirection.FORWARD);
            assertEquals(new Vector2d(2, 3), animal.getPosition());

            grassField.move(animal, MoveDirection.RIGHT);
            grassField.move(animal, MoveDirection.FORWARD);
            assertEquals(new Vector2d(3, 3), animal.getPosition());
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCannotPlaceAnimalOnOccupiedPosition() throws IncorrectPositionException {
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));
        try {
            assertTrue(grassField.place(animal1));
            assertFalse(grassField.place(animal2));
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testMapVisualization() throws IncorrectPositionException {
        GrassField grassField = new GrassField(5);
        Animal animal = new Animal(new Vector2d(2, 2));
        try {
            grassField.place(animal);
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }

        String mapRepresentation = grassField.toString();
        assertNotNull(mapRepresentation);
        //System.out.println(mapRepresentation);
    }

}