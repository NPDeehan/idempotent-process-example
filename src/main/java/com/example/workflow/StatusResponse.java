package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class StatusResponse implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer askTimes = (Integer) delegateExecution.getVariable("askTimes");
        String status = "Status unknown";

        if(between(askTimes, 0, 2)){
            status = "Still Working on the question";
        }else if(between(askTimes, 3, 5)){
            status = "I am working on it - you've already asked "+ askTimes + " times!";
        }else if(between(askTimes, 6, 9)){
            status = "Seriously, you need to be patient " + askTimes + " is enough times";
        }else if(askTimes > 9){
            status = "Asking " + askTimes + " will not get your answer any faster";
        }

        askTimes = askTimes+1;

        delegateExecution.setVariable("status", status);
        delegateExecution.setVariable("askTimes", askTimes);

    }

    public static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
        if (i >= minValueInclusive && i <= maxValueInclusive)
            return true;
        else
            return false;
    }
}
