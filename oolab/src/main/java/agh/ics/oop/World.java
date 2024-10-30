package agh.ics.oop;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import static agh.ics.oop.OptionsParser.*;
import agh.ics.oop.model.Vector2d;
import java.util.List;
import java.util.ArrayList;

public class World {
    public static void main(String[] args) {
//        System.out.println("Start");
//        MoveDirection[] moves = parse(args);
//        run(moves);
//        System.out.println("Stop");
//        Vector2d position1 = new Vector2d(1, 2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2, 1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//        MapDirection[] test = {MapDirection.SOUTH, MapDirection.EAST};
//        System.out.println(test[0].previous());
//        System.out.println(test[1].toUnitVector());
//        Do zadania lepiej użyć ArrayLista ze względu na łatwość dostępu do elemetów
        Animal animal = new Animal(new Vector2d(1, 2));
        System.out.println(animal.toString());
        if(animal.isAt(new Vector2d(1, 2))) {
            System.out.println("Pozycja się zgadza");
        }

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();



    }

    public static void run(MoveDirection[] moves) {
        //System.out.println("zwierzak idzie do przodu");
        for(MoveDirection arg : moves) {
            switch (arg) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }
}
