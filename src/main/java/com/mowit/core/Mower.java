package com.mowit.core;

import com.mowit.core.command.Command;
import com.mowit.core.command.CommandFactory;
import com.mowit.core.exception.InvalidMovingCommand;
import com.mowit.core.exception.InvalidMowerStartingPosition;
import com.mowit.core.geo.Coordinates;
import com.mowit.core.geo.Direction;
import com.mowit.core.geo.Obstacles;
import com.mowit.core.geo.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class Mower implements Callable<Position> {

    private final Lawn lawn;
    private Position position;
    private final List<Command> commands;

    public Mower(Lawn lawn) {
        this.lawn = lawn;
        this.commands = new LinkedList<>();
    }

    public void createStartingPosition(String position) throws InvalidMowerStartingPosition {
        String[] inputPosition = position.split(" ");
        if (inputPosition.length != 3)
            throw new InvalidMowerStartingPosition(position);

        Position startingPosition = new Position(getCoordinates(inputPosition), Direction.getDirectionFromString(inputPosition[2]));
        if (Obstacles.currentMowsPosition.containsValue(startingPosition.getCoordinates()))
            throw new InvalidMowerStartingPosition(position);

        Obstacles.currentMowsPosition.put(System.identityHashCode(this), startingPosition.getCoordinates());
        this.position = startingPosition;
    }

    private Coordinates getCoordinates(String[] inputPosition) throws InvalidMowerStartingPosition {
        String startingCoordinates = String.join(" ", inputPosition[0], inputPosition[1]);
        if (Coordinates.isInvalidCoordinates(startingCoordinates))
            throw new InvalidMowerStartingPosition(startingCoordinates);

        Coordinates coordinates = new Coordinates(startingCoordinates);
        if (lawn.isOutLawn(coordinates)) {
            throw new InvalidMowerStartingPosition(startingCoordinates);
        }
        return coordinates;
    }


    public Position getPosition() {
        return this.position;
    }

    public void createCommands(String instructions) throws InvalidMovingCommand {
        for (char c : instructions.toCharArray()) {
            this.commands.add(CommandFactory.getCommandFromChar(c));
        }
    }

    public List<Command> getCommands() {
        return this.commands;
    }

    public void goLeft() {
        position.goLeft();
    }

    public void goRight() {
        position.goRight();
    }

    public void goForward() {
        Coordinates nextCoordinates = position.getNextCoordinates();
        if (!lawn.isOutLawn(nextCoordinates) && !Obstacles.currentMowsPosition.containsValue(nextCoordinates))
            this.position.setCoordinates(nextCoordinates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mower mower = (Mower) o;

        if (!Objects.equals(lawn, mower.lawn)) return false;
        if (!Objects.equals(position, mower.position)) return false;
        return Objects.equals(commands, mower.commands);
    }

    @Override
    public int hashCode() {
        int result = lawn != null ? lawn.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + commands.hashCode();
        return result;
    }

    @Override
    public Position call() {
        this.executeCommands();
        return this.position;
    }

    private void executeCommands() {
        this.commands.forEach(this::executeCommand);
    }

    private void executeCommand(Command command) {
        command.execute(this);
        Obstacles.currentMowsPosition.put(System.identityHashCode(this), this.position.getCoordinates());
    }

    public void init(String startingPosition, String instructions) throws InvalidMovingCommand, InvalidMowerStartingPosition {
        this.createStartingPosition(startingPosition);
        this.createCommands(instructions);
    }
}
