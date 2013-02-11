package zyake.apps.jenkinsjobexecutor.senders;

import zyake.apps.jenkinsjobexecutor.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobRequestSenderFactory {

    protected static Map<String, Class<JobRequestSender>> senderClasses = new ConcurrentHashMap<>();

    public static void configure(Map<String, String> requestSenders) {
        for ( Map.Entry<String, String> senderEntry : requestSenders.entrySet() ) {
            boolean duplicatedKeyFound = senderClasses.containsKey(senderEntry.getKey());
            if ( duplicatedKeyFound ) {
                throw new SenderException("duplicated key found: key=" + senderEntry.getKey());
            }

            Class<JobRequestSender> senderClass = ClassUtils.loadClass(senderEntry.getValue());
            boolean isNotSender = ! ClassUtils.hasInterface(senderClass, JobRequestSender.class);
            if ( isNotSender ) {
                throw new SenderException("class is not sender: FQCN=" + senderClass);
            }

            senderClasses.put(senderEntry.getKey(), senderClass);
        }
    }

    public static JobRequestSender newInstance(String sender, Map<String, String> props) {
        boolean senderNotFound = ! senderClasses.containsKey(sender);
        if ( senderNotFound ) {
            throw new SenderException("sender not found: key=" + sender);
        }

        Class<JobRequestSender> jobRequestSenderClass = senderClasses.get(sender);
        JobRequestSender jobRequestSender = ClassUtils.newInstance(jobRequestSenderClass);
        jobRequestSender.initialize(props);

        return jobRequestSender;
    }

}
