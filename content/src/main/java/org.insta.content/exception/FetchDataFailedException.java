package org.insta.content.exception;

import org.insta.exception.DefaultException;

/**
 * <p>
 * Exception class for handle data fetch.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see DefaultException
 */
public final class FetchDataFailedException extends DefaultException {

    /**
     * <p>
     * Constructs an FetchDataFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public FetchDataFailedException(final String message) {
        super(message);
    }
}
