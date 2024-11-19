package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    // additional test for lab4

    @Test
    void twoAnimalsInAndOneOut() {
        String[] args = {"f", "r", "b", "l", "f", "b"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(2,2));
        WorldMap map = new RectangularMap(7, 7);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animalsList = simulation.getAnimals();
        List<Vector2d> currentPositions = new ArrayList<>();

        for(Animal animal: animalsList) {
            currentPositions.add(animal.getPosition());
        }

        List<Vector2d> correctPositions = new ArrayList<>();
        correctPositions.add(new Vector2d(1, 3));
        assertEquals(correctPositions, currentPositions);
    }

    @Test
    void puttingValidMovesList() {
        String[] args = {"f", "r", "b", "l", "f", "b"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 3), new Vector2d(1,4));
        WorldMap map = new RectangularMap(7, 7);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animalsList = simulation.getAnimals();
        List<Vector2d> currentPositions = new ArrayList<>();

        for(Animal animal: animalsList) {
            currentPositions.add(animal.getPosition());
        }

        List<Vector2d> correctPositions = new ArrayList<>();
        correctPositions.add(new Vector2d(2, 4));
        correctPositions.add(new Vector2d(1, 3));
        assertEquals(correctPositions, currentPositions);
    }

    @Test
    void puttingValidAndInvalidMovesList() {
        String[] args = {"f", "x", "y", "r", "b", "z", "l", "f", "b", "a", "c"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 3), new Vector2d(1,4));
        WorldMap map = new RectangularMap(7, 7);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animalsList = simulation.getAnimals();
        List<Vector2d> currentPositions = new ArrayList<>();

        for(Animal animal: animalsList) {
            currentPositions.add(animal.getPosition());
        }

        List<Vector2d> correctPositions = new ArrayList<>();
        correctPositions.add(new Vector2d(2, 4));
        correctPositions.add(new Vector2d(1, 3));
        assertEquals(correctPositions, currentPositions);
    }

    @Test
    void puttingEmptyMovesList() {
        String[] args = {};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 3), new Vector2d(1,4));
        WorldMap map = new RectangularMap(7, 7);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animalsList = simulation.getAnimals();
        List<Vector2d> currentPositions = new ArrayList<>();

        for(Animal animal: animalsList) {
            currentPositions.add(animal.getPosition());
        }

        List<Vector2d> correctPositions = new ArrayList<>();
        correctPositions.add(new Vector2d(2, 3));
        correctPositions.add(new Vector2d(1, 4));
        assertEquals(correctPositions, currentPositions);
    }

    @Test
    void testingBoundsWhenGoingForward() {
        String[] args = {"f", "r", "r", "l", "f", "f", "r", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parse(args); // north, east, south, west
        List<Vector2d> positions = List.of(new Vector2d(1, 2), new Vector2d(4,0), new Vector2d(3,0), new Vector2d(0,3));
        WorldMap map = new RectangularMap(4, 4);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animalsList = simulation.getAnimals();
        List<Vector2d> currentPositions = new ArrayList<>();

        for(Animal animal: animalsList) {
            currentPositions.add(animal.getPosition());
        }

        List<Vector2d> correctPositions = new ArrayList<>();
        correctPositions.add(new Vector2d(1, 4));
        correctPositions.add(new Vector2d(4, 0));
        correctPositions.add(new Vector2d(3, 0));
        correctPositions.add(new Vector2d(0, 3));
        assertEquals(correctPositions, currentPositions);
    }

    @Test
    void testingBoundsWhenGoingBackward() {
        String[] args = {"b", "r", "r", "l", "b", "b", "r", "b", "b", "b", "b", "b"};
        List<MoveDirection> directions = OptionsParser.parse(args); // north, east, south, west
        List<Vector2d> positions = List.of(new Vector2d(1, 2), new Vector2d(1,0), new Vector2d(3,4), new Vector2d(3,3));
        WorldMap map = new RectangularMap(4, 4);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animalsList = simulation.getAnimals();
        List<Vector2d> currentPositions = new ArrayList<>();

        for(Animal animal: animalsList) {
            currentPositions.add(animal.getPosition());
        }

        List<Vector2d> correctPositions = new ArrayList<>();
        correctPositions.add(new Vector2d(1, 0));
        correctPositions.add(new Vector2d(0, 0));
        correctPositions.add(new Vector2d(3, 4));
        correctPositions.add(new Vector2d(4, 3));
        assertEquals(correctPositions, currentPositions);
    }

    @Test
    void checkingAnimalsOrientation() {
        String[] args = {"l", "l", "r", "l", "r", "l", "l", "l", "l", "r", "r", "r", "r", "l", "r", "r", "r", "r"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(1, 2), new Vector2d(1,0), new Vector2d(3,4), new Vector2d(3,3));
        WorldMap map = new RectangularMap(7, 7);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animalsList = simulation.getAnimals();
        List<MapDirection> currentOrientations = new ArrayList<>();

        for(Animal animal: animalsList) {
            currentOrientations.add(animal.getOrientation());
        }

        List<MapDirection> correctOrientations = new ArrayList<>();
        correctOrientations.add(MapDirection.EAST);
        correctOrientations.add(MapDirection.WEST);
        correctOrientations.add(MapDirection.SOUTH);
        correctOrientations.add(MapDirection.NORTH);
        assertEquals(correctOrientations, currentOrientations);
    }

    @Test
    void isAnimalGoingOnRightPlaceAndIsStartingPositionValid() {
        String[] args = {"f", "b", "r", "l", "f", "b"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(2, 2), new Vector2d(12, 15), new Vector2d(-5, -4));
        WorldMap map = new RectangularMap(7, 7);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animalsList = simulation.getAnimals();
        List<Vector2d> currentPositions = new ArrayList<>();

        for(Animal animal: animalsList) {
            currentPositions.add(animal.getPosition());
        }

        List<Vector2d> correctPositions = new ArrayList<>();
        correctPositions.add(new Vector2d(2, 2));
        assertEquals(correctPositions, currentPositions);
    }
}