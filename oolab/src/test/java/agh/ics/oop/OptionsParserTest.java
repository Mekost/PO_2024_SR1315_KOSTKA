package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void isMovingForwardCorrect() {
        String[] test = {"f"};
        List<MoveDirection> result = List.of(MoveDirection.FORWARD);
        try {
            assertEquals(result, OptionsParser.parse(test));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isMovingLeftCorrect() {
        String[] test = {"l"};
        List<MoveDirection> result = List.of(MoveDirection.LEFT);
        try {
            assertEquals(result, OptionsParser.parse(test));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isMovingRightCorrect() {
        String[] test = {"r"};
        List<MoveDirection> result = List.of(MoveDirection.RIGHT);
        try {
            assertEquals(result, OptionsParser.parse(test));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isMovingBackwardCorrect() {
        String[] test = {"b"};
        List<MoveDirection> result = List.of(MoveDirection.BACKWARD);
        try {
            assertEquals(result, OptionsParser.parse(test));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isCheckingWrongCommandCorrect() {
        String[] test = {"t"};
        List<MoveDirection> result = List.of();
        try {
            assertEquals(result, OptionsParser.parse(test));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isCheckingEmptyArrayCorrect() {
        String[] test = {};
        List<MoveDirection> result = List.of();
        try {
            assertEquals(result, OptionsParser.parse(test));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isCheckingWholeArrayCorrect() {
        String[] test = {"f", "r", "b", "t", "l"};
        List<MoveDirection> result = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT);
        try {
            assertEquals(result, OptionsParser.parse(test));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}