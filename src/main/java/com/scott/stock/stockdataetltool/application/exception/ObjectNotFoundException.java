package com.scott.stock.stockdataetltool.application.exception;

public class ObjectNotFoundException extends Exception {

    private Object id;
    private String message;

    public ObjectNotFoundException(Object id) {
        super();
        message = String.format("ID '%s' not found.", String.valueOf(id));
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
