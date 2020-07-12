package com.mowit.core;

import com.mowit.core.exception.InvalidCommand;
import com.mowit.core.exception.InvalidMovingCommand;
import com.mowit.core.exception.InvalidMowerStartingPosition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

class MowersController {

    private List<Mower> mowers;

    public MowersController() {
        super();
    }

    public void processFile(File inputCommands) throws IOException, InvalidCommand {
        List<Mower> extractedMowers = new LinkedList<>();
        try(FileInputStream inputStream = new FileInputStream(inputCommands);
            Scanner sc = new Scanner(inputStream, "UTF-8")){
            String lawnDimension = sc.nextLine();
            Lawn lawn = new Lawn();
            lawn.createLimitLawnCoordinates(lawnDimension);
            if(!sc.hasNextLine()){
                throw new InvalidMowerStartingPosition("Empty command");
            }
            while (sc.hasNextLine()) {
                String startingPosition = sc.nextLine();
                if(!sc.hasNextLine()){
                    throw new InvalidMovingCommand("Empty command");
                }
                String instructions = sc.nextLine();
                Mower mower = new Mower(lawn);
                mower.createStartingPosition(startingPosition);
                mower.createCommands(instructions);
                extractedMowers.add(mower);
            }
        }
        this.mowers= Collections.unmodifiableList(extractedMowers);
    }

    public void startMowing() {
        throw new UnsupportedOperationException();
    }

    public List<Mower> getMowers() {
        return this.mowers;
    }
}
