package zyake.apps.jenkinsjobexecutor.loaders;

import zyake.apps.jenkinsjobexecutor.Job;
import zyake.apps.jenkinsjobexecutor.config.Argument;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface JobLoader extends AutoCloseable {

    List<Job> loadJobs(String url);

    void initialize(Map<String, String> props);
}
