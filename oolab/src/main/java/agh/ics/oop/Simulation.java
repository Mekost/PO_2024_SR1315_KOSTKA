package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Vector2d> positions;
    private List<MoveDirection> directions;
    private int amountOfAnimals;
    private List<Animal> animals;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions) {
        this.positions = positions;
        this.directions = directions;
        this.amountOfAnimals = positions.size();
        this.animals = new ArrayList<>();
        for (int i=0; i<positions.size(); i++){
            if (positions.get(i).get_x() >= 0 && positions.get(i).get_x() <= 4 && positions.get(i).get_y() >= 0 && positions.get(i).get_y() <= 4)
            {
                animals.add(new Animal(positions.get(i)));
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
            animals.get(animalId).move(currentMove);
            System.out.println("Zwierzę %d : ".formatted(animalId) + animals.get(animalId).toString());
            animalId += 1;
            if (animalId >= animals.size()) {
                animalId = 0;
            }
        }
    }
}
