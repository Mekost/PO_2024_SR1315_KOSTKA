package agh.ics.oop;
import agh.ics.oop.model.*;

import static agh.ics.oop.OptionsParser.*;

import java.util.List;
import java.util.ArrayList;

public class World {
    public static void main(String[] args) {
        try {
//        AbstractWorldMap map = new GrassField(10);
//        AbstractWorldMap map2 = new RectangularMap(6,6);
//        map.addObserver(new ConsoleMapDisplay());
//        map2.addObserver(new ConsoleMapDisplay());
//        Simulation simulation = new Simulation(positions, directions, map);
//        Simulation simulation2 = new Simulation(positions, directions, map2);
//        SimulationEngine engine = new SimulationEngine(List.of(simulation, simulation2));
//        engine.runAsyncInThreadPool();
//        engine.awaitSimulationsEnd();
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        List<Simulation> simulations = new ArrayList<>();
//        for(int i = 0; i < 2000; i++) {
//            AbstractWorldMap map = new GrassField(10);
//            map.addObserver(new ConsoleMapDisplay());
//            Simulation simulation = new Simulation(positions, directions, map);
//            simulations.add(simulation);
//        }
        for(int i = 0; i < 7; i++) {
            AbstractWorldMap map = new GrassField(10);
            map.addObserver(new ConsoleMapDisplay());
            Simulation simulation = new Simulation(positions, directions, map);
            simulations.add(simulation);
        }
        SimulationEngine engine = new SimulationEngine(simulations);
        engine.runAsyncInThreadPool();
        engine.awaitSimulationsEnd();
        } catch(IllegalArgumentException e) {
            System.out.println("Denied: " + e.getMessage());
            return;
        }
        System.out.println("System zakończył działanie");
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
