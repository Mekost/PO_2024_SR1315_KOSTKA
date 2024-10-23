package agh.ics.oop.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    // given
    Vector2d a = new Vector2d(0, 0);
    Vector2d b = new Vector2d(3, 4);
    Vector2d c = new Vector2d(-4, -3);
    Vector2d d = new Vector2d(-2, 3);

    @Test
    void checkingConversionToString() {
        assertEquals("(0,0)", a.toString());
    }

    @Test
    void equalityOfVectors() {
        assertTrue(b.equals(b));
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
        assertFalse(c.equals(d));
    }
    @Test
    void findingUpperRightPoint() {
        assertEquals(new Vector2d(-2, 3), c.upperRight(d));
        assertEquals(new Vector2d(3, 4), a.upperRight(b));
    }

    @Test
    void findingLowerLeftPoint() {
        assertEquals(new Vector2d(-4, -3), c.lowerLeft(d));
        assertEquals(new Vector2d(0, 0), a.lowerLeft(b));
    }
    @Test
    void isPrecedingCorrect() {
        assertTrue(a.precedes(b));
        assertFalse(d.precedes(c));
    }
    @Test
    void isFollowingCorrect() {
        assertFalse(a.follows(b));
        assertTrue(d.follows(c));
    }

    @Test
    void checkingAddition() {
        assertEquals(new Vector2d(3, 4), a.add(b));
        assertEquals(new Vector2d(-6, 0), c.add(d));
    }

    @Test
    void checkingSubtraction() {
        assertEquals(new Vector2d(-3, -4), a.subtract(b));
        assertEquals(new Vector2d(-2, -6), c.subtract(d));
    }

    @Test
    void oppositeFunction() {
        assertEquals(new Vector2d(4, 3), c.opposite());
        assertEquals(new Vector2d(2, -3), d.opposite());
    }

}
