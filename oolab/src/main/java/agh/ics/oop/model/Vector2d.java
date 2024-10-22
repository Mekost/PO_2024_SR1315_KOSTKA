package agh.ics.oop.model;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(%s,%d)".formatted(this.x, this.y);
    }

    public boolean precedes(Vector2d other) {
        return other.x >= this.x && other.y >= this.y;
    }

    public boolean follows(Vector2d other) {
        return other.x <= x && other.y <= y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other) {
        int new_x = Math.max(this.x, other.x);
        int new_y = Math.max(this.y, other.y);
        return new Vector2d(new_x, new_y);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int new_x = Math.min(this.x, other.x);
        int new_y = Math.min(this.y, other.y);
        return new Vector2d(new_x, new_y);
    }

    public Vector2d opposite() {
        return new Vector2d(this.x * (-1), this.y * (-1));
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d vector = (Vector2d) other;
        return this.x == vector.x && this.y == vector.y;
    }
}
