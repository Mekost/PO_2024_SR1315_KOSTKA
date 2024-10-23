package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        int size = args.length;
        //System.out.println(size);
        MoveDirection[] moves = new MoveDirection[size];
        for (int i = 0; i < size; i++) {
            switch (args[i]) {
                case "r" -> moves[i] = MoveDirection.RIGHT;
                case "l" -> moves[i] = MoveDirection.LEFT;
                case "f" -> moves[i] = MoveDirection.FORWARD;
                case "b" -> moves[i] = MoveDirection.BACKWARD;
                default -> moves[i] = MoveDirection.WRONG;
            }
        }
        return moves;
    }
}
