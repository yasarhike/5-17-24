package org.insta.content.exception.story;

/**
 * <p>
 * Exception class for handle story creation failed.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryException
 */
public final class StoryCreationFailedException extends StoryException {

    /**
     * <p>
     * Constructs an StoryCreationFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryCreationFailedException(final String message) {
        super(message);
    }
}
