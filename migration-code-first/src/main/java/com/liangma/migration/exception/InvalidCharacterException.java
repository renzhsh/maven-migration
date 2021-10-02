package com.liangma.migration.exception;

public class InvalidCharacterException extends MigrationException {
    public InvalidCharacterException() {
        super();
    }

    public InvalidCharacterException(String message) {
        super(message);
    }

    public InvalidCharacterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCharacterException(Throwable cause) {
        super(cause);
    }

    protected InvalidCharacterException(String message, Throwable cause,
                                        boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
