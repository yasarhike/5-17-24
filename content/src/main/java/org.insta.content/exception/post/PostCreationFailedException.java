package org.insta.content.exception.post;

/**
 * <p>
 * Exception class for handle post creation failed.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostException
 */
public final class PostCreationFailedException extends PostException {

    /**
     * <p>
     * Constructs an PostCreationFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public PostCreationFailedException(final String message) {
        super(message);
    }
}
