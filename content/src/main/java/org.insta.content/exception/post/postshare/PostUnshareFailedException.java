package org.insta.content.exception.post.postshare;

import org.insta.content.exception.post.PostException;

/**
 * <p>
 * Exception class for handle post share removal.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostException
 */
public final class PostUnshareFailedException extends PostException {

    /**
     * <p>
     * Constructs an PostUnshareFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public PostUnshareFailedException(final String message) {
        super(message);
    }
}
