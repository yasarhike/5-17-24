package org.insta.content.exception.post.postlike;

import org.insta.content.exception.post.PostException;

/**
 * <p>
 * Exception class for handle Post Like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostException
 */
public final class PostLikeFailedException extends PostException {

    /**
     * <p>
     * Constructs an PostLikeFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public PostLikeFailedException(final String message) {
        super(message);
    }
}
