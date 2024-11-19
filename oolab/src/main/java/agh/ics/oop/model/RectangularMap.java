package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int height;
    private final Vector2d bottomLeft;
    private final Vector2d topRight;
    private final MapVisualizer visualizer;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.bottomLeft = new Vector2d(0, 0);
        this.topRight = new Vector2d(width - 1, height - 1);
        lowerLeft = bottomLeft;
        upperRight = topRight;
        this.visualizer = new MapVisualizer(this);
    }

//    @Override
//    public boolean place(Animal animal) {
//        if (animal.getPosition().follows(bottomLeft) && animal.getPosition().precedes(topRight) && !isOccupied(animal.getPosition())) {
//            animals.put(new Vector2d(animal.getPosition().getX(), animal.getPosition().getY()), animal);
//            return true;
//        }
//        return false;
//    }

//    @Override
//    public void move(Animal animal, MoveDirection moveDirection) {
//        Vector2d previousLocation = animal.getPosition();
//        animal.move(moveDirection, this);
//        animals.remove(previousLocation);
//        animals.put(animal.getPosition(), animal);
//    }

//    @Override
//    public boolean isOccupied(Vector2d position) {
//        return animals.containsKey(position);
//    }

    public String toString() {
        return visualizer.draw(bottomLeft, topRight);
    }

//    @Override
//    public Animal objectAt(Vector2d position) {
//        return animals.get(position);
//    }

//    @Override
//    public boolean canMoveTo(Vector2d position) {
//        return !isOccupied(position) && position.precedes(topRight) && position.follows(bottomLeft);
//    }


}
