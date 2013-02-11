package zyake.apps.jenkinsjobexecutor.senders;

import zyake.apps.jenkinsjobexecutor.Job;

import java.util.Map;

public interface JobRequestSender extends AutoCloseable {

    void initialize(Map<String, String> props);

    void disableJob(Job job);

    void enableJob(Job job) ;

    void executeJob(Job job);
}
