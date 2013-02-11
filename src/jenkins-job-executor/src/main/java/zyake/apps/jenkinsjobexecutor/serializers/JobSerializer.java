package zyake.apps.jenkinsjobexecutor.serializers;

import zyake.apps.jenkinsjobexecutor.Job;

import java.util.Map;

public interface JobSerializer {

    public void initialize(Map<String, String> props);

    public void serializeJob(Job job);

    public void finish();
}
