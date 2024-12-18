package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimulationApp extends Application {

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();

//        String[] args = getParameters().getRaw().toArray(new String[0]);
//        List<MoveDirection> directions = OptionsParser.parse(args);
//        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
//        AbstractWorldMap map = new GrassField(10);
//        presenter.setWorldMap(map);
//        map.addObserver(new ConsoleMapDisplay());
//        map.addObserver(new SimulationPresenter());
//        Simulation simulation = new Simulation(positions, directions, map);
//        SimulationEngine engine = new SimulationEngine(List.of(simulation));
//        engine.runAsyncInThreadPool();
//        engine.awaitSimulationsEnd();
        configureStage(primaryStage, viewRoot);
        primaryStage.show();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
//        primaryStage.show();
    }

}
