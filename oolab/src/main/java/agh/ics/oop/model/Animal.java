package agh.ics.oop.model;

public class Animal implements WorldElement {
    private MapDirection currentOrientation;
    private Vector2d currentLocation;

    public Animal() {
        this.currentOrientation = MapDirection.NORTH;
        this.currentLocation = new Vector2d(2, 2);
    }

    public Animal(Vector2d currentLocation) {
        this.currentLocation = currentLocation;
        this.currentOrientation = MapDirection.NORTH;
    }

    public MapDirection getOrientation() {
        return currentOrientation;
    }

    public Vector2d getPosition() {
        return currentLocation;
    }

    public String toString() {
        return currentOrientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.currentLocation.getX() == position.getX() && this.currentLocation.getY() == position.getY();
    }

    public boolean isInBoundaries(int width, int height) {
        return currentLocation.getX() >= 0 && currentLocation.getX() <= width && currentLocation.getY() >= 0 && currentLocation.getY() <= height;
    }


    public void move(MoveDirection direction, MoveValidator moveValidator) {
        switch (direction) {
            case MoveDirection.RIGHT-> this.currentOrientation = this.currentOrientation.next();
            case MoveDirection.LEFT -> this.currentOrientation = this.currentOrientation.previous();
            case MoveDirection.FORWARD -> {
                this.currentLocation = this.currentLocation.add(this.currentOrientation.toUnitVector());
                if (!moveValidator.canMoveTo(currentLocation)) {
                    System.out.println("You can't go outside the bounds");
                    this.currentLocation = this.currentLocation.subtract(this.currentOrientation.toUnitVector());
                }
            }
            case MoveDirection.BACKWARD -> {
                this.currentLocation = this.currentLocation.subtract(this.currentOrientation.toUnitVector());
                if (!moveValidator.canMoveTo(currentLocation)) {
                    System.out.println("You can't go outside the bounds");
                    this.currentLocation = this.currentLocation.add(this.currentOrientation.toUnitVector());
                }
            }
        }


        }
    }

