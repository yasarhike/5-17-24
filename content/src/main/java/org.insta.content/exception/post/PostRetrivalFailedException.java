package org.insta.content.exception.post;

/**
 * <p>
 * Exception class for handle Post Retrival.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostException
 */
public final class PostRetrivalFailedException extends PostException {

    /**
     * <p>
     * Constructs an PostRetrivalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public PostRetrivalFailedException(final String message) {
        super(message);
    }
}
