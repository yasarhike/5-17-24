package org.insta.content.exception.reel.reelshare;

import org.insta.content.exception.reel.ReelException;

/**
 * <p>
 * Exception class for handle reel share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelException
 */
public final class ReelShareFailedException extends ReelException {

    /**
     * <p>
     * Constructs an ReelShareFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelShareFailedException(final String message) {
        super(message);
    }
}
