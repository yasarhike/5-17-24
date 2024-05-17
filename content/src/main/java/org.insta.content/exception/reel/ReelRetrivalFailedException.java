package org.insta.content.exception.reel;

/**
 * <p>
 * Exception class for handle reel retrival.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelException
 */
public final class ReelRetrivalFailedException extends ReelException {

    /**
     * <p>
     * Constructs an ReelRetrivalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelRetrivalFailedException(final String message) {
        super(message);
    }
}
