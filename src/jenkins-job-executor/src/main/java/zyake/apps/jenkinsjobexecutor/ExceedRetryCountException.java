package zyake.apps.jenkinsjobexecutor;


public class ExceedRetryCountException extends RuntimeException {

    private Job job;

    public ExceedRetryCountException(Job job) {
        this.job = job;
    }
}
