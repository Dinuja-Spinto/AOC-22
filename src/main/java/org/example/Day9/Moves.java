package org.example.Day9;

public class Moves {
    private final String direction;
    private final int steps;

    public Moves(String direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public String getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }
}
