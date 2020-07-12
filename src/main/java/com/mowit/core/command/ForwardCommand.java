package com.mowit.core.command;

import com.mowit.core.Mower;

public class ForwardCommand implements Command {


    @Override
    public void execute(Mower mower) {
        mower.goForward();
    }
}
