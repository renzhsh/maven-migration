package com.liangma.migration.exception;

public class NotFoundKeyException extends MigrationException {
    public NotFoundKeyException() {
        super();
    }

    public NotFoundKeyException(String message) {
        super(message);
    }

    public NotFoundKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundKeyException(Throwable cause) {
        super(cause);
    }

    protected NotFoundKeyException(String message, Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
