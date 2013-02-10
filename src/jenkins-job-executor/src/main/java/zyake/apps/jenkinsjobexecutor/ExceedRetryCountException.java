package zyake.apps.jenkinsjobexecutor;

/**
 * Created with IntelliJ IDEA.
 * User: zyake
 * Date: 13/02/10
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class ExceedRetryCountException extends RuntimeException {
    public ExceedRetryCountException(Job job) {
    }
}
