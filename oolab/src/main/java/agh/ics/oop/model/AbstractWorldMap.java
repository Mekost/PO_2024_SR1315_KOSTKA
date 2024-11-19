package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);
    protected Vector2d lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    protected Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    @Override
    public void move(Animal animal, MoveDirection moveDirection) {
        Vector2d prevPosition = animal.getPosition();
        animal.move(moveDirection, this);
        animals.remove(prevPosition);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal) && position.precedes(upperRight) && position.follows(lowerLeft);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.get(position) != null) return animals.get(position);
        return null;
    }

    public Map<Vector2d, WorldElement> getElements() {
        Map<Vector2d, WorldElement> elements = new HashMap<>();
        for (Map.Entry<Vector2d, Animal> element : animals.entrySet()) {
            elements.put(element.getKey(), element.getValue());
        }
        return elements;
    }
}
