package com.liangma.migration;

import com.liangma.migration.exception.MigrationException;

public class NotFoundYmlException extends MigrationException {
    public NotFoundYmlException() {
        super();
    }

    public NotFoundYmlException(String message) {
        super(message);
    }

    public NotFoundYmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundYmlException(Throwable cause) {
        super(cause);
    }

    protected NotFoundYmlException(String message, Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
