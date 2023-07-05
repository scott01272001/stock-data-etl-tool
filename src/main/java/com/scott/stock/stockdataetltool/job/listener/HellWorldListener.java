package com.scott.stock.stockdataetltool.job.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

public class HellWorldListener extends JobListenerSupport {

    private String name = "Hell-World-Listener";

    @Override
    public String getName() {
        return "Hell-World-Listener";
    }

    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println(name + " execute");
    }

    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println(name + " vetoed");
    }

    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println(name + " was execute");
    }

}
