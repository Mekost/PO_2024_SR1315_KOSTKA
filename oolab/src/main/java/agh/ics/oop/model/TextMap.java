package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.swap;

public class TextMap implements WorldNumberPositionMap<String, Integer> {
    private List<String> list = new ArrayList<>();
//    private int currentLength = 0;
    private Map<String, Integer> texts = new HashMap<>();

    @Override
    public boolean canMoveTo(Integer position) {
        return position >= 0 && position < list.size();
    }

    public boolean place(String string){
        if (texts.containsKey(string)) {
            return false;
        }
        else {
            texts.put(string, list.size());
            list.add(string);
            return true;
        }
    }


    public boolean exists(String string) {
        return texts.containsKey(string);
    }


    @Override
    public void move(String element, MoveDirection direction) {
        boolean isDirectionInValid = !(direction == MoveDirection.FORWARD || direction == MoveDirection.BACKWARD);
        if (isDirectionInValid || !exists(element)) {
            return;
        }

        int oldPosition = texts.get(element);
        int newPosition = switch (direction) {
            case FORWARD -> oldPosition + 1;
            case BACKWARD -> oldPosition - 1;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };

        if (canMoveTo(newPosition)) {
            String neighbour = list.get(newPosition);
            swap(list, newPosition, oldPosition);
            this.texts.put(element, newPosition);
            this.texts.put(neighbour, oldPosition);
        }
    }

    public boolean isOccupied(Integer position){
        return 0<=position && position< list.size();
    }
    @Override
    public String objectAt(Integer position){
        if (position >= 0 && position < list.size()) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public String toString() {
        return texts.values().toString();
    }
}
