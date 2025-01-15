package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void getOrderedAnimals() {
        AbstractWorldMap testMap = new GrassField(10);
        AbstractWorldMap testMap2 = new RectangularMap(10, 10);
        try {
            testMap.place(new Animal(new Vector2d(3,2)));
            testMap.place(new Animal(new Vector2d(4,5)));
            testMap.place(new Animal(new Vector2d(1,8)));
            testMap.place(new Animal(new Vector2d(6,2)));
            testMap2.place(new Animal(new Vector2d(6,1)));
            testMap2.place(new Animal(new Vector2d(8,0)));
            testMap2.place(new Animal(new Vector2d(6,5)));
            testMap2.place(new Animal(new Vector2d(1,9)));
        } catch (IncorrectPositionException e) {
            throw new RuntimeException(e);
        }
        List<Animal> animals1 = testMap.getOrderedAnimals();
        List<Animal> animals2 = testMap2.getOrderedAnimals();
        assertEquals(List.of(1, 3, 4, 6), List.of(animals1.get(0).getPosition().getX(), animals1.get(1).getPosition().getX(), animals1.get(2).getPosition().getX(), animals1.get(3).getPosition().getX()));
        assertEquals(List.of(9, 1, 5, 0), List.of(animals2.get(0).getPosition().getY(), animals2.get(1).getPosition().getY(), animals2.get(2).getPosition().getY(), animals2.get(3).getPosition().getY()));
    }
}