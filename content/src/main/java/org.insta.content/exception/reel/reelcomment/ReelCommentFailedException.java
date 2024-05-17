package org.insta.content.exception.reel.reelcomment;

import org.insta.content.exception.reel.ReelException;

/**
 * <p>
 * Exception class for handle reel comment.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelException
 */
public final class ReelCommentFailedException extends ReelException {

    /**
     * <p>
     * Constructs an ReelCommentFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelCommentFailedException(final String message) {
        super(message);
    }
}
