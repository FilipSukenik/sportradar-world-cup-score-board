/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board;

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

    public TechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }
}
