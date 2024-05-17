package org.insta.content.exception.story;

import org.insta.exception.DefaultException;

/**
 * <p>
 * Exception class for manage story.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see DefaultException
 */
public class StoryException extends DefaultException {

    /**
     * <p>
     * Constructs an StoryException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryException(final String message) {
        super(message);
    }
}
