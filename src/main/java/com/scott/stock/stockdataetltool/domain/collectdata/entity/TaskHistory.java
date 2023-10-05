package com.scott.stock.stockdataetltool.domain.collectdata.entity;

public class TaskHistory {

    private long id;
    private Task task;
    private Status status;
    private String log;

    public enum Status {
        Success, Failed;
    }
}
