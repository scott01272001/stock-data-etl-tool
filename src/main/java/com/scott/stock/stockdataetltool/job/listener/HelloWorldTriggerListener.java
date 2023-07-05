package com.scott.stock.stockdataetltool.job.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.listeners.TriggerListenerSupport;

public class HelloWorldTriggerListener extends TriggerListenerSupport {

    private String name = "Hello-World-Trigger-Listener";

    @Override
    public String getName() {
        return name;
    }

    public void triggerFired(Trigger trigger, JobExecutionContext context) {
    }

    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    public void triggerMisfired(Trigger trigger) {
        System.out.println(name + " mis fired");
    }

    public void triggerComplete(
        Trigger trigger,
        JobExecutionContext context,
        CompletedExecutionInstruction triggerInstructionCode) {

        System.out.println(name + " Complete");
    }

}
