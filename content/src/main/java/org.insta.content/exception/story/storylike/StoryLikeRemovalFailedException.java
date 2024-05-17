package org.insta.content.exception.story.storylike;

import org.insta.content.exception.story.StoryException;

/**
 * <p>
 * Exception class for handle story like removal.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryException
 */
public final class StoryLikeRemovalFailedException extends StoryException {

    /**
     * <p>
     * Constructs an StoryLikeRemovalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryLikeRemovalFailedException(final String message) {
        super(message);
    }
}
