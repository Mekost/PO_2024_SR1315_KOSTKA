package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);
    protected Vector2d lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    protected Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected List<MapChangeListener> observers = new ArrayList<>();
    protected Map<Vector2d, WorldElement> elements = new HashMap<>();
    protected int mapID = this.hashCode();

    @Override
    public void move(Animal animal, MoveDirection moveDirection) {
        Vector2d prevPosition = animal.getPosition();
        animal.move(moveDirection, this);
        animals.remove(prevPosition);
        animals.put(animal.getPosition(), animal);
        notifyObservers("Zwierze ruszylo sie z pozycji " + prevPosition + " na " + animal.getPosition());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return objectAt(position)
                .map(element -> !(element instanceof Animal))
                .orElse(true)
                && position.precedes(upperRight)
                && position.follows(lowerLeft);
    }


    @Override
    public boolean place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            notifyObservers("Animal placed at " + animal.getPosition());
            return true;
        }
        else {
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return Optional.of(animals.get(position));
        }
        return Optional.empty();
    }

    public Map<Vector2d, WorldElement> getElements() {
        animals.entrySet().stream()
                .forEach(entry -> elements.put(entry.getKey(), entry.getValue()));
        return elements;
    }


    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeft, upperRight);
    }

    public String toString() {
        return visualizer.draw(getCurrentBounds().bottomLeftCorner(), getCurrentBounds().upperRightCorner());
    }

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void deleteObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String string) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, string);
        }
    }

    public int getId() {
        return mapID;
    }

    public List<Animal> getOrderedAnimals() {
        Comparator<Animal> comparator = Comparator
                .comparing((Animal animal) -> animal.getPosition().getX())
                .thenComparing((Animal animal) -> animal.getPosition().getY());

        return animals.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
