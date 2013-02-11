package zyake.apps.jenkinsjobexecutor.reports;

import zyake.apps.jenkinsjobexecutor.Job;

import java.util.List;
import java.util.Map;

public interface JobReportWriter {

    void initialize(Map<String, String> props);

    void writeJobs(List<Job> jobs);

    void finish();
}
