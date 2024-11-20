package agh.ics.oop;
import agh.ics.oop.model.*;

import static agh.ics.oop.OptionsParser.*;

import java.util.List;
import java.util.ArrayList;

public class World {
    public static void main(String[] args) {

        WorldMap map = new GrassField(10);
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        Simulation simulation = new Simulation(positions, directions, map);
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
