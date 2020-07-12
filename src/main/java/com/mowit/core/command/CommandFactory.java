package com.mowit.core.command;


import com.mowit.core.exception.InvalidMovingCommand;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum CommandFactory {
    LEFT('G', new LeftCommand()),
    RIGHT('D', new RightCommand()),
    FORWARD('A', new ForwardCommand());

    Character codeCmd;
    Command command;

    private static Map<Character, Command> commandFactory;

    static {
        commandFactory = new HashMap<>();
        Map<Character, Command> map = new HashMap<>();
        for (CommandFactory value : CommandFactory.values()) {
            map.put(value.getCodeCmd(), value.getCommand());
        }
        commandFactory = Collections.unmodifiableMap(map);
    }

    CommandFactory(Character codeCmd, Command command) {
        this.codeCmd = codeCmd;
        this.command = command;
    }

    public Character getCodeCmd() {
        return codeCmd;
    }

    public Command getCommand() {
        return command;
    }


    public static Command getCommandFromChar(Character character) throws InvalidMovingCommand {
        return Optional.ofNullable(commandFactory.get(character))
                .orElseThrow(() -> new InvalidMovingCommand(String.valueOf(character)));
    }


}