package com.mowit.core.exception;

public enum ErrorMessages {
    EMPTY_MOWER_INITIAL_POSITION("Empty command: expected a valid mower initial position"),
    EMPTY_MOWER_MOVING_COMMANDS("Empty command: expected a valid moving commands for the mower n°:");

    private final String msg;

    ErrorMessages(String s) {
        this.msg = s;
    }

    public String getMsg() {
        return msg;
    }
}