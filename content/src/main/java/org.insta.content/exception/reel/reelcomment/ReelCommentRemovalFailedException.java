package org.insta.content.exception.reel.reelcomment;

import org.insta.content.exception.reel.ReelException;

/**
 * <p>
 * Exception class for handle reel comment removal.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelException
 */
public final class ReelCommentRemovalFailedException extends ReelException {

    /**
     * <p>
     * Constructs an ReelCommentRemovalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelCommentRemovalFailedException(final String message) {
        super(message);
    }
}
