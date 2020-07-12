package com.mowit.core;

import com.mowit.core.exception.InvalidCommand;
import com.mowit.core.exception.InvalidFilePath;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MowIt {
    public static void main(String[] args) throws InvalidFilePath, IOException, InvalidCommand, ExecutionException, InterruptedException {
        if(args.length==0)
            throw new InvalidFilePath();
        File instructionsFile= new File(args[0]);
        if(!instructionsFile.exists())
            throw new InvalidFilePath();
        MowersController controller = new MowersController();
        controller.processFile(instructionsFile);
        controller.startMowing();
    }
}
