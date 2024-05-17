package org.insta.content.exception.story;

/**
 * <p>
 * Exception class for handle story remove.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryException
 */
public final class StoryRemovalFailedException extends StoryException {

    /**
     * <p>
     * Constructs an StoryRemovalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryRemovalFailedException(final String message) {
        super(message);
    }
}
