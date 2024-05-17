package org.insta.content.exception.reel;

import org.insta.exception.DefaultException;

/**
 * <p>
 * Exception class for handle reel exceptions.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see DefaultException
 */
public class ReelException extends DefaultException {

    /**
     * <p>
     * Constructs an ReelException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelException(final String message) {
        super(message);
    }
}
