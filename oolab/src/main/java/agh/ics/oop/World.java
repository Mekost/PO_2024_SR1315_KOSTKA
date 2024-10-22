package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        MoveDirection[] moves = parse(args);
        run(moves);
        System.out.println("Stop");
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
