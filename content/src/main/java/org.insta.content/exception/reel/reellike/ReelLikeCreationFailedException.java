package org.insta.content.exception.reel.reellike;

import org.insta.content.exception.reel.ReelException;
import org.insta.exception.DefaultException;

/**
 * <p>
 * Exception class for handle reel like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelException
 */
public final class ReelLikeCreationFailedException extends DefaultException {

    /**
     * <p>
     * Constructs an ReelLikeCreationFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelLikeCreationFailedException(final String message) {
        super(message);
    }
}
