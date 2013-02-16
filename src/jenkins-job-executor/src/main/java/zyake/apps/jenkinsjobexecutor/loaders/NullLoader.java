package zyake.apps.jenkinsjobexecutor.loaders;

import zyake.apps.jenkinsjobexecutor.Job;
import zyake.apps.jenkinsjobexecutor.util.Predicate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NullLoader implements JobLoader {

    @Override
    public List<Job> loadJobs(String url) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Job> loadJobs(String url, Predicate<Job> predicate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void initialize(Map<String, String> props) {
    }

    @Override
    public void close() throws Exception {
    }
}
