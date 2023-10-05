package com.scott.stock.stockdataetltool.application.exception;

public class DuplicatedObjectException extends Exception {

    private String message;

    public DuplicatedObjectException(String msg) {
        message = String.format("Duplicate, %s", msg);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
