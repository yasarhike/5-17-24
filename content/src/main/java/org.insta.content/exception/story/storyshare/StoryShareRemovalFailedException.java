package org.insta.content.exception.story.storyshare;

import org.insta.content.exception.story.StoryException;

/**
 * <p>
 * Exception class for handle story share removal.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryException
 */
public final class StoryShareRemovalFailedException extends StoryException {

    /**
     * <p>
     * Constructs an StoryShareRemovalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryShareRemovalFailedException(final String message) {
        super(message);
    }
}
