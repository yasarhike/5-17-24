package org.insta.content.exception.post.postcomment;

import org.insta.content.exception.post.PostException;

/**
 * <p>
 * Exception class for handle Post comment remove.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostException
 */
public final class PostUncommentFailedException extends PostException {

    /**
     * <p>
     * Constructs an PostUncommentFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public PostUncommentFailedException(final String message) {
        super(message);
    }
}
