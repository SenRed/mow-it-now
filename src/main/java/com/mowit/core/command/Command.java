package com.mowit.core.command;


import com.mowit.core.Mower;

public interface Command {

    void execute(Mower mower);
}
