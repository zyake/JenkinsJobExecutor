package zyake.apps.jenkinsjobexecutor;

public class ExecutorException extends RuntimeException {
    public ExecutorException(String s) {
        super(s);
    }

    public ExecutorException(String msg, Exception e) {
        super(msg, e);
    }
}
