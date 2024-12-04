package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import java.util.List;
import java.util.ArrayList;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        int size = args.length;
        //System.out.println(size);
        List<MoveDirection> moves = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            switch (args[i]) {
                case "r" -> moves.add(MoveDirection.RIGHT);
                case "l" -> moves.add(MoveDirection.LEFT);
                case "f" -> moves.add(MoveDirection.FORWARD);
                case "b" -> moves.add(MoveDirection.BACKWARD);
                default -> throw new IllegalArgumentException(args[i] + "is not legal move specification");
            }
        }
        return moves;
    }
}
