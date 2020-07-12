package com.mowit.core;

import com.mowit.core.exception.InvalidFilePath;

import java.io.File;

public class MowIt {
    public static void main(String[] args) throws InvalidFilePath {
        if(args.length==0)
            throw new InvalidFilePath();
        File instructionsFile= new File(args[0]);
        if(!instructionsFile.exists())
            throw new InvalidFilePath();
        throw new UnsupportedOperationException();
    }
}
