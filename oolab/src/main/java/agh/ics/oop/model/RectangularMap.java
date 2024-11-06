package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap<Animal, Vector2d> {
    private Map<Vector2d, Animal> animals = new HashMap<>();
    private int width;
    private int height;
    private final Vector2d bottomLeft;
    private final Vector2d upperRight;
    private final MapVisualizer visualizer;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.bottomLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
        this.visualizer = new MapVisualizer(this);
    }

    @Override
    public boolean place(Animal animal) {
        if (animal.getLocation().follows(bottomLeft) && animal.getLocation().precedes(upperRight) && !isOccupied(animal.getLocation())) {
            animals.put(new Vector2d(animal.getLocation().getX(), animal.getLocation().getY()), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection moveDirection) {
        Vector2d previousLocation = animal.getLocation();
        animal.move(moveDirection, this);
        animals.remove(previousLocation);
        animals.put(animal.getLocation(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public String toString() {
        return visualizer.draw(bottomLeft, upperRight);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && position.precedes(upperRight) && position.follows(bottomLeft);
    }


}
