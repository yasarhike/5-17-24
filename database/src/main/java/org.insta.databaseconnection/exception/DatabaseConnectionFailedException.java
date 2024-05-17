package org.insta.databaseconnection.exception;

import org.insta.exception.DefaultException;

public final class DatabaseConnectionFailedException extends DefaultException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public DatabaseConnectionFailedException(final String message) {
        super(message);
    }
}
