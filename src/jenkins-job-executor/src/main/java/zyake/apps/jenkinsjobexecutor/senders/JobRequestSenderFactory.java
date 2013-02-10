package zyake.apps.jenkinsjobexecutor.senders;

/**
 * Created with IntelliJ IDEA.
 * User: zyake
 * Date: 13/02/10
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */
public class JobRequestSenderFactory {
    public static void configure(Object requestSenders) {
    }

    public static JobRequestSender newInstance(String sender) {
        return new JobRequestSender();
    }
}
