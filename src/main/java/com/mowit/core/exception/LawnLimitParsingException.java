package com.mowit.core.exception;

public class LawnLimitParsingException extends InvalidCommand{
    public LawnLimitParsingException(String dimensions) {
        super("cause "+dimensions);
    }
}
