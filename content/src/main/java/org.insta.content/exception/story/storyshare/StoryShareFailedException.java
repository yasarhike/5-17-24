package org.insta.content.exception.story.storyshare;

import org.insta.content.exception.story.StoryException;

/**
 * <p>
 * Exception class for handle story share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryException
 */
public class StoryShareFailedException extends StoryException {

    /**
     * <p>
     * Constructs an StoryShareFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryShareFailedException(final String message) {
        super(message);
    }
}
