package zyake.apps.jenkinsjobexecutor.senders;

import zyake.apps.jenkinsjobexecutor.Job;

import java.util.Map;

public class NullRequestSender implements JobRequestSender
{
    @Override
    public void initialize(Map<String, String> props) {
    }

    @Override
    public void disableJob(Job job) {
    }

    @Override
    public void enableJob(Job job) {
    }

    @Override
    public void executeJob(Job job) {
    }

    @Override
    public void close() throws Exception {
    }
}
