package org.insta.content.exception.reel;

/**
 * <p>
 * Exception class for manage reel creation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelException
 */
public final class ReelCreationFailedException extends ReelException {

    /**
     * <p>
     * Constructs an ReelCreationFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelCreationFailedException(final String message) {
        super(message);
    }
}
