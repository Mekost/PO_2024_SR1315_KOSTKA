package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
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
            Animal animal = new Animal(position);
            if (map.place(animal))
            {
                map.place(animal);
                animals.add(animal);
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
            System.out.println(map);
            animalId += 1;
            if (animalId >= animals.size()) {
                animalId = 0;
            }
        }
    }
}
