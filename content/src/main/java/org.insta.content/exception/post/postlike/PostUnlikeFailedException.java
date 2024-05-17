package org.insta.content.exception.post.postlike;

import org.insta.content.exception.post.PostException;

/**
 * <p>
 * Exception class for handle post like remove.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostException
 */
public class PostUnlikeFailedException extends PostException {

    /**
     * <p>
     * Constructs an PostUnlikeFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public PostUnlikeFailedException(final String message) {
        super(message);
    }
}
