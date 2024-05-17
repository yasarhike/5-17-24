package org.insta.content.exception.reel;

import org.insta.exception.DefaultException;

/**
 * <p>
 * Exception class for handle reel deletion.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelException
 */
public final class ReelRemovalFailedException extends DefaultException {

    /**
     * <p>
     * Constructs an ReelRemovalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelRemovalFailedException(final String message) {
        super(message);
    }
}
