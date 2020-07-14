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

    private final Character codeCmd;
    private final Command command;

    private static Map<Character, Command> lookup;

    static {
        lookup = new HashMap<>();
        Map<Character, Command> map = new HashMap<>();
        for (CommandFactory value : CommandFactory.values()) {
            map.put(value.getCodeCmd(), value.getCommand());
        }
        lookup = Collections.unmodifiableMap(map);
    }

    CommandFactory(Character codeCmd, Command command) {
        this.codeCmd = codeCmd;
        this.command = command;
    }

    private Character getCodeCmd() {
        return codeCmd;
    }

    private Command getCommand() {
        return command;
    }


    public static Command getCommandFromChar(Character character) throws InvalidMovingCommand {
        return Optional.ofNullable(lookup.get(character))
                .orElseThrow(() -> new InvalidMovingCommand(String.valueOf(character)));
    }


}