package com.scott.stock.stockdataetltool.application.exception;

public class ConnectionException extends Exception {

    private final int code;
    private final String message;

    public ConnectionException(int code, String message) {
        super(String.format("code = %d, msg: %s", code, message));
        this.code = code;
        this.message = message;
    }

}
