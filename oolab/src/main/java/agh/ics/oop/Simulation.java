package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    private List<Vector2d> positions;
    private List<MoveDirection> directions;
    private int amountOfAnimals;
    private List<Animal> animals;
    private final WorldMap map;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap map) {
        this.positions = positions;
        this.directions = directions;
        this.amountOfAnimals = positions.size();
        this.animals = new ArrayList<>();
        this.map = map;
        for (Vector2d position : positions){
            try {
                Animal animal = new Animal(position);
                if (map.place(animal)) {
                    animals.add(animal);
                }
            } catch (IncorrectPositionException e) {
                System.out.println("Denied: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<MoveDirection> getDirections() {
        return directions;
    }

    public void run() {
        int animalId = 0;
        for(int i = 0; i < directions.size(); i++) {
            MoveDirection currentMove = directions.get(i);
            map.move(animals.get(animalId), currentMove);
            System.out.println("Zwierzę %d : ".formatted(animalId) + animals.get(animalId).toString());
            animalId += 1;
            if (animalId >= animals.size()) {
                animalId = 0;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
