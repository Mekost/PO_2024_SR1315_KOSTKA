package agh.ics.oop.model;


import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    private Map<Vector2d, Grass> grassLocations = new HashMap<>();
    private int amountOfGrassFields;

    public GrassField(int amountOfGrass) {
        this.amountOfGrassFields = amountOfGrass;
        this.visualizer = new MapVisualizer(this);
        int upperBound = (int) Math.sqrt(amountOfGrassFields * 10);
//        while (grassLocations.size() < amountOfGrassFields) {
//            int x = getRandom(0, upperBound);
//            int y = getRandom(0, upperBound);
//            Vector2d possiblePlacing = new Vector2d(x, y);
//            if (!grassLocations.containsKey(possiblePlacing)) {
//                grassLocations.put(possiblePlacing, new Grass(possiblePlacing));
//            }
//        }
////        grassLocations.put(new Vector2d(2, 2), new Grass(new Vector2d(2,2)));
        // ^ pierwotny, niedeterministyczny algorytm

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(upperBound, upperBound, amountOfGrassFields);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grassLocations.put(grassPosition, new Grass(grassPosition));
        }
    }

    public Map<Vector2d, Grass> getGrassLocations() {
        return grassLocations;
    }

    public static int getRandom(int min, int max) {

        int range = (max - min) + 1;
        int random = (int) ((range * Math.random()) + min);
        return random;
    }


//    public boolean place(Animal animal) {
//        if (!isOccupied(animal.getPosition())) {
//            animals.put(new Vector2d(animal.getPosition().getX(), animal.getPosition().getY()), animal);
//            return true;
//        }
//        return false;
//    }

//    @Override
//    public String toString() {
//        int biggest_width = 0;
//        int biggest_height = 0;
//        int lowest_width = 0;
//        int lowest_height = 0;
//        for (Map.Entry<Vector2d, Animal> entry : animals.entrySet()) {
//            Animal currentAnimal = entry.getValue();
//            biggest_height = Math.max(biggest_height, currentAnimal.getPosition().getY());
//            biggest_width = Math.max(biggest_width, currentAnimal.getPosition().getX());
//            lowest_width = Math.min(lowest_width, currentAnimal.getPosition().getX());
//            lowest_height = Math.min(lowest_height, currentAnimal.getPosition().getY());
//        }
//        for (Map.Entry<Vector2d, Grass> entry : grassLocations.entrySet()) {
//            Grass currentGrass = entry.getValue();
//            biggest_height = Math.max(biggest_height, currentGrass.getPosition().getY());
//            biggest_width = Math.max(biggest_width, currentGrass.getPosition().getX());
//            lowest_width = Math.min(lowest_width, currentGrass.getPosition().getX());
//            lowest_height = Math.min(lowest_height, currentGrass.getPosition().getY());
//        }
//
//        return visualizer.draw(new Vector2d(lowest_width, lowest_height), new Vector2d(biggest_width, biggest_height));
//    }

//    public void move(Animal animal, MoveDirection moveDirection) {
//        Vector2d prevPosition = animal.getPosition();
//        animal.move(moveDirection, this);
//        animals.remove(prevPosition);
//        animals.put(animal.getPosition(), animal);
//    }

    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grassLocations.containsKey(position);
    }

    public Optional<WorldElement> objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return Optional.of(animals.get(position));
        }
        if (grassLocations.containsKey(position)) {
            return Optional.of(grassLocations.get(position));
        }
        return Optional.empty();
    }

    public Map<Vector2d, WorldElement> getElements() {
        return Stream.concat(
                super.getElements().entrySet().stream(),
                grassLocations.entrySet().stream()
        ).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (existing, replacement) -> replacement instanceof Animal ? replacement : existing
        ));
    }





    @Override
    public Boundary getCurrentBounds() {
        int biggest_width = 0;
        int biggest_height = 0;
        int lowest_width = 0;
        int lowest_height = 0;
        Map<Vector2d, WorldElement> elements = this.getElements();
        for(Map.Entry<Vector2d, WorldElement> entry: elements.entrySet()){
            WorldElement currentElement = entry.getValue();
            biggest_height = Math.max(biggest_height, currentElement.getPosition().getY());
            biggest_width = Math.max(biggest_width, currentElement.getPosition().getX());
            lowest_width = Math.min(lowest_width, currentElement.getPosition().getX());
            lowest_height = Math.min(lowest_height, currentElement.getPosition().getY());
        }
        return new Boundary(new Vector2d(lowest_width, lowest_height), new Vector2d(biggest_width, biggest_height));
    }

}
