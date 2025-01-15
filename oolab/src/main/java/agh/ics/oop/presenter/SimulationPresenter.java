package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField listOfMoves;
    @FXML
    private Label descriptionLabel;
    @FXML
    private GridPane mapGrid;
    private int width = 100;
    private int height = 100;
    private int maxWidth = 300;
    private int maxHeight = 300;

    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private int mapWidth;
    private int mapHeight;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void drawMap() {
        clearGrid();
        updateBounds();
        xyLabel();
        columnsFunction();
        rowsFunction();
        addElements();
        mapGrid.setGridLinesVisible(true);
    }

    public void mapChanged(WorldMap map, String message) {
        this.map = map;
        Platform.runLater(() -> {
            clearGrid();
            drawMap();
            descriptionLabel.setText(message);
        });
    }

    @FXML
    private void onSimulationStartClicked(){
        String moveList = listOfMoves.getText();

        List<MoveDirection> directions = parse(moveList.split(" "));
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap map = new GrassField(10);
        map.addObserver(this);
        this.map = map;
        Simulation simulation = new Simulation(positions, directions, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        descriptionLabel.setText("Simulation started with moves: " + moveList);
        new Thread(() -> {
            engine.runAsync();
        }).start();
    }

    private void clearGrid(){
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    public void xyLabel(){
        mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
        mapGrid.getRowConstraints().add(new RowConstraints(height));
        Label label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
    }

    public void updateBounds(){
        xMin = map.getCurrentBounds().bottomLeftCorner().getX();
        yMin = map.getCurrentBounds().bottomLeftCorner().getY();
        xMax = map.getCurrentBounds().upperRightCorner().getX();
        yMax = map.getCurrentBounds().upperRightCorner().getY();
        mapWidth = xMax - xMin + 1;
        mapHeight = yMax - yMin + 1;
        width = Math.round(maxWidth/mapWidth);
        height = Math.round(maxHeight/mapHeight);
        height = Math.min(height, width);
        width = height;
    }

    public void columnsFunction(){
        for(int i=0; i<mapWidth; i++){
            Label label = new Label(Integer.toString(i+xMin));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
            mapGrid.add(label, i+1, 0);
        }
    }

    public void rowsFunction(){
        for(int i=0; i<mapHeight; i++){
            Label label = new Label(Integer.toString(yMax-i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(height));
            mapGrid.add(label, 0, i+1);
        }
    }

    public void addElements(){
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                Vector2d pos = new Vector2d(i, j);
                if (map.isOccupied(pos)) {
                    mapGrid.add(new Label(map.objectAt(pos).toString()), i - xMin + 1, yMax - j + 1);
                }
                else {
                    mapGrid.add(new Label(" "), i - xMin + 1, yMax - j + 1);
                }
                mapGrid.setHalignment(mapGrid.getChildren().get(mapGrid.getChildren().size() - 1), HPos.CENTER);
            }
        }
    }


}
