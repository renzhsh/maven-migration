package com.liangma.migration.exception;

public class InvalidOperateException extends MigrationException {
    public InvalidOperateException() {
        super();
    }

    public InvalidOperateException(String message) {
        super(message);
    }

    public InvalidOperateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOperateException(Throwable cause) {
        super(cause);
    }

    protected InvalidOperateException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
