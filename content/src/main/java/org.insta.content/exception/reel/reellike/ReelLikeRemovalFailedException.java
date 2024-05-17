package org.insta.content.exception.reel.reellike;

import org.insta.content.exception.reel.ReelException;
import org.insta.exception.DefaultException;

/**
 * <p>
 * Exception class for handle reel like removal.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelException
 */
public final class ReelLikeRemovalFailedException extends DefaultException {

    /**
     * <p>
     * Constructs an ReelLikeRemovalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelLikeRemovalFailedException(final String message) {
        super(message);
    }
}
