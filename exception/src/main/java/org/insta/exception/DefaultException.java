package org.insta.exception;

/**
 * <p>
 * Custom runtime exception class specific to Instagram-related errors.
 * </p>
 *
 * <p>
 * This exception can be thrown to indicate exceptional conditions encountered
 * during Instagram-related operations. It extends {@link RuntimeException},
 * making it an unchecked exception.
 * </p>
 *
 * @see RuntimeException
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class DefaultException extends RuntimeException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
     public DefaultException(final String message) {
        super(message);
    }
}
