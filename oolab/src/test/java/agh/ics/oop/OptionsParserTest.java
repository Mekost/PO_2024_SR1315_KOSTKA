package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void isMovingForwardCorrect() {
        String[] test = {"f"};
        MoveDirection[] result = {MoveDirection.FORWARD};
        assertArrayEquals(result, OptionsParser.parse(test));
    }

    @Test
    void isMovingLeftCorrect() {
        String[] test = {"l"};
        MoveDirection[] result = {MoveDirection.LEFT};
        assertArrayEquals(result, OptionsParser.parse(test));
    }

    @Test
    void isMovingRightCorrect() {
        String[] test = {"r"};
        MoveDirection[] result = {MoveDirection.RIGHT};
        assertArrayEquals(result, OptionsParser.parse(test));
    }

    @Test
    void isMovingBackwardCorrect() {
        String[] test = {"b"};
        MoveDirection[] result = {MoveDirection.BACKWARD};
        assertArrayEquals(result, OptionsParser.parse(test));
    }

    @Test
    void isCheckingWrongCommandCorrect() {
        String[] test = {"t"};
        MoveDirection[] result = {MoveDirection.WRONG};
        assertArrayEquals(result, OptionsParser.parse(test));
    }

    @Test
    void isCheckingWholeArrayCorrect() {
        String[] test = {"f", "r", "b", "t", "l"};
        MoveDirection[] result = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.WRONG, MoveDirection.LEFT};
        assertArrayEquals(result, OptionsParser.parse(test));
    }

}