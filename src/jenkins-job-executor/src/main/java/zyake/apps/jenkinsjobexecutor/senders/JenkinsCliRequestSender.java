package zyake.apps.jenkinsjobexecutor.senders;

import hudson.cli.CLI;
import zyake.apps.jenkinsjobexecutor.Job;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class JenkinsCliRequestSender implements JobRequestSender {

    private CLI client;

    protected JenkinsCliRequestSender(String url) {
        try {
            this.client = new CLI(new URL(url));
        } catch (IOException e) {
            throw new SenderException("instantination failed: url=" + url, e);
        } catch (InterruptedException e) {
            throw new SenderException("instantination failed: url=" + url, e);
        }
    }

    @Override
    public void initialize(Map<String, String> props) {
    }

    @Override
    public void disableJob(Job job) {
        client.execute("disable-job", job.getName());
    }

    @Override
    public void enableJob(Job job) {
        client.execute("enable-job", job.getName());
    }

    @Override
    public void executeJob(Job job) {
        client.execute("build", job.getName());
    }

    @Override
    public void close() throws Exception {
        client.close();
    }
}
