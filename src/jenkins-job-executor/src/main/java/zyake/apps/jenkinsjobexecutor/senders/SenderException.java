package zyake.apps.jenkinsjobexecutor.senders;

public class SenderException extends RuntimeException {

    public SenderException(String msg, Exception e) {
        super(msg,e);
    }

    public SenderException(String msg) {
        super(msg);
    }
}
