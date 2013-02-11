package zyake.apps.jenkinsjobexecutor.loaders;

import zyake.apps.jenkinsjobexecutor.Job;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NullLoader implements JobLoader {

    @Override
    public List<Job> loadJobs(String url) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void initialize(Map<String, String> props) {
    }

    @Override
    public void close() throws Exception {
    }
}
