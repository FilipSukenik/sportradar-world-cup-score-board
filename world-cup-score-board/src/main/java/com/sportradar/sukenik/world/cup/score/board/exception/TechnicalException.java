package com.sportradar.sukenik.world.cup.score.board.exception;

/**
 * Wraps all unexpected internal exceptions. In case that there is bug in the library and the unexpected
 * {@link RuntimeException} is thrown, the original exception is wrapped in {@link TechnicalException}.
 */
public class TechnicalException extends RuntimeException {

    public TechnicalException() {

    }

    public TechnicalException(String message) {

        super(message);
    }

    public TechnicalException(String message, Throwable cause) {

        super(message, cause);
    }

    public TechnicalException(Throwable cause) {

        super(cause);
    }
}
