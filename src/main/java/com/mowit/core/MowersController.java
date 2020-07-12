package com.mowit.core;

import com.mowit.core.exception.InvalidCommand;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class MowersController {

    public MowersController() {
        super();
    }

    public void processFile(File inputCommands) throws IOException, InvalidCommand {
        try(FileInputStream inputStream = new FileInputStream(inputCommands);
            Scanner sc = new Scanner(inputStream, "UTF-8")){
            String lawnDimension = sc.nextLine();
            Lawn lawn = new Lawn();
            lawn.createLimitLawnCoordinates(lawnDimension);
            while (sc.hasNextLine()) {
                String startingPosition = sc.nextLine();
                Mower mower = new Mower(lawn);
                mower.createStartingPosition(startingPosition);
                String instructions = sc.nextLine();
                mower.createCommands(instructions);
            }
        }
    }

    public void startMowing() {
        throw new UnsupportedOperationException();
    }
}
