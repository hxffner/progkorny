package hu.nye.albums.model.exception;

import java.io.Serial;

/**
 * NotFoundException.
 */
public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2974108486715092114L;

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
