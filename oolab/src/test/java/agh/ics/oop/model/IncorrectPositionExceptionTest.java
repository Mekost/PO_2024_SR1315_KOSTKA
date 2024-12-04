package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncorrectPositionExceptionTest {

    @Test
    void testExceptionMessage() {
        Vector2d position = new Vector2d(3, 4);
        IncorrectPositionException exception = new IncorrectPositionException(position);
        assertEquals("Position (3, 4) is not correct", exception.getMessage());
    }

    @Test
    void testPositionGetter() {
        Vector2d position = new Vector2d(-1, 0);
        IncorrectPositionException exception = new IncorrectPositionException(position);
        assertEquals(position, exception.getPosition());
    }

}