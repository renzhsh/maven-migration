package com.liangma.migration.exception;

public class NotFoundExpressionException extends MigrationException {
    public NotFoundExpressionException() {
        super();
    }

    public NotFoundExpressionException(String message) {
        super(message);
    }

    public NotFoundExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundExpressionException(Throwable cause) {
        super(cause);
    }

    protected NotFoundExpressionException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
