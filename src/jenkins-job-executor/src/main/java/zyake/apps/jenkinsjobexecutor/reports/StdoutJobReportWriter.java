package zyake.apps.jenkinsjobexecutor.reports;

import zyake.apps.jenkinsjobexecutor.Job;

import java.util.List;
import java.util.Map;

public class StdoutJobReportWriter implements JobReportWriter {

    @Override
    public void initialize(Map<String, String> props) {
    }

    @Override
    public void writeJobs(List<Job> jobs) {
        for ( Job job : jobs ) {
            System.out.println(job);
        }
    }

    @Override
    public void finish() {
    }
}
