package org.insta.authentication.exception;

import org.insta.exception.DefaultException;

/**
 * <p>
 * Exception class representing a failure in a database operation within the Instagram application.
 * This exception is typically thrown when there is an issue while performing database operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see DefaultException
 */
public class DatabaseOperationFailed extends DefaultException {

    /**
     * <p>
     * Constructs a new DatabaseOperationFailed exception with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public DatabaseOperationFailed(final String message) {
        super(message);
    }
}
