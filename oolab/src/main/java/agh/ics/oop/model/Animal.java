package agh.ics.oop.model;

public class Animal {
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

    public Vector2d getLocation() {
        return currentLocation;
    }

    public String toString() {
        return "(Current orientation: %s, current position: (%d,%d))".formatted(this.currentOrientation, this.currentLocation.get_x(), this.currentLocation.get_y());
    }

    public boolean isAt(Vector2d position) {
        return this.currentLocation.get_x() == position.get_x() && this.currentLocation.get_y() == position.get_y();
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case MoveDirection.RIGHT-> this.currentOrientation = this.currentOrientation.next();
            case MoveDirection.LEFT -> this.currentOrientation = this.currentOrientation.previous();
            case MoveDirection.FORWARD -> {
                this.currentLocation = this.currentLocation.add(this.currentOrientation.toUnitVector());
                if (this.currentLocation.get_x() > 4 || this.currentLocation.get_y() > 4 || this.currentLocation.get_x() < 0 || this.currentLocation.get_y() < 0) {
                    System.out.println("You can't go outside the bounds");
                    this.currentLocation = this.currentLocation.subtract(this.currentOrientation.toUnitVector());
                }
            }
            case MoveDirection.BACKWARD -> {
                this.currentLocation = this.currentLocation.subtract(this.currentOrientation.toUnitVector());
                if (this.currentLocation.get_x() > 4 || this.currentLocation.get_y() > 4 || this.currentLocation.get_x() < 0 || this.currentLocation.get_y() < 0) {
                    System.out.println("You can't go outside the bounds");
                    this.currentLocation = this.currentLocation.add(this.currentOrientation.toUnitVector());
                }
            }
        }


        }
    }

