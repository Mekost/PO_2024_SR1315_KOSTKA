package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {
    private List<Vector2d> positions;
    private List<MoveDirection> directions;
    private List<T> animals;
    private final WorldMap<T, P> map;

    public Simulation(List<T> animals, List<MoveDirection> directions, WorldMap<T, P> map) {
        this.animals = animals;
        this.directions = directions;
        this.map = map;
    }

    public List<T> getAnimals() {
        return animals;
    }

    public List<MoveDirection> getDirections() {
        return directions;
    }

    public void run() {
        int animalId = 0;
        System.out.println(map);
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
