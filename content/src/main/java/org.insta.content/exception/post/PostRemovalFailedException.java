package org.insta.content.exception.post;

/**
 * <p>
 * Exception class for handle post removal.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostException
 */
public final class PostRemovalFailedException extends PostException {

    /**
     * <p>
     * Constructs an PostException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public PostRemovalFailedException(final String message) {
        super(message);
    }
}
