package org.insta.content.exception.story;

/**
 * <p>
 * Exception class for handle story retrieve.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryException
 */
public final class StoryRetrivalFailedException extends StoryException {

    /**
     * <p>
     * Constructs an StoryRetrivalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryRetrivalFailedException(final String message) {
        super(message);
    }
}
