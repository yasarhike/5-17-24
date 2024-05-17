package org.insta.authentication.exception;

import org.insta.exception.DefaultException;

/**
 * <p>
 * Exception class representing a failure in profile deletion within the Instagram application.
 * This exception is typically thrown when there is an issue while deleting a user profile.
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
public class ProfileDeleteFailedException extends DefaultException {

    /**
     * <p>
     * Constructs a new ProfileDeleteFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ProfileDeleteFailedException(final String message) {
        super(message);
    }
}
