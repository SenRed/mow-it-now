package com.mowit.core.command;

import com.mowit.core.Mower;

public class LeftCommand implements Command {


    @Override
    public void execute(Mower mower) {
        mower.goLeft();
    }
}
