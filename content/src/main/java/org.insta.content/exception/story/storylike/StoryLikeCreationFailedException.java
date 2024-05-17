package org.insta.content.exception.story.storylike;

import org.insta.content.exception.story.StoryException;

/**
 * <p>
 * Exception class for handle story like creation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryException
 */
public final class StoryLikeCreationFailedException extends StoryException {

    /**
     * <p>
     * Constructs an StoryLikeCreationFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryLikeCreationFailedException(final String message) {
        super(message);
    }
}
