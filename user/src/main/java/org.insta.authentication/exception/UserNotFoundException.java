package org.insta.authentication.exception;

import org.insta.exception.DefaultException;

/**
 * <p>
 * Exception class representing a user not found error within the Instagram application.
 * This exception is typically thrown when attempting to access a user who does not exist.
 * </p>
 *
 * <p>
 * This class extends the InstagramException class and is part of the exception hierarchy for Instagram-related exceptions.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see DefaultException
 */
public class UserNotFoundException extends DefaultException {

    /**
     * <p>
     * Constructs a new UserNotFoundException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
