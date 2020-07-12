package com.mowit.core.command;

import com.mowit.core.Mower;

public class RightCommand implements Command {

    @Override
    public void execute(Mower mower) {
        mower.goRight();
    }
}