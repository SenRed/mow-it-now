package com.mowit.core;

import com.mowit.core.command.Command;
import com.mowit.core.command.CommandFactory;
import com.mowit.core.exception.InvalidMovingCommand;
import com.mowit.core.exception.InvalidMowerStartingPosition;
import com.mowit.core.geo.Coordinates;
import com.mowit.core.geo.Direction;
import com.mowit.core.geo.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class Mower implements Callable<Position> {

    Lawn lawn;
    Position position;
    List<Command> commands;

    public Mower(Lawn lawn) {
        this.lawn = lawn;
        this.commands = new LinkedList<>();
    }

    public void createStartingPosition(String position) throws InvalidMowerStartingPosition {
        String[] inputPosition = position.split(" ");
        if (inputPosition.length != 3)
            throw new InvalidMowerStartingPosition(position);

        Coordinates coordinates = getCoordinates(position, inputPosition);
        Direction direction = Direction.getDirectionFromString(inputPosition[2]);

        this.position = new Position(coordinates, direction);
    }

    private Coordinates getCoordinates(String position, String[] inputPosition) throws InvalidMowerStartingPosition {
        String startingCoordinates = String.join(" ", inputPosition[0], inputPosition[1]);
        if (Coordinates.isInvalidCoordinates(startingCoordinates))
            throw new InvalidMowerStartingPosition(position);

        Coordinates coordinates = new Coordinates(startingCoordinates);
        if (lawn.isOutLawn(coordinates)) {
            throw new InvalidMowerStartingPosition(position);
        }
        return coordinates;
    }

    public Lawn getLawn() {
        return lawn;
    }

    public void setLawn(Lawn lawn) {
        this.lawn = lawn;
    }

    public void setPosition(Position position) {
        this.position = position;
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
        if(!lawn.isOutLawn(nextCoordinates))
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
        result = 31 * result + (commands != null ? commands.hashCode() : 0);
        return result;
    }

    @Override
    public Position call()  {
        return this.position;
    }
}
