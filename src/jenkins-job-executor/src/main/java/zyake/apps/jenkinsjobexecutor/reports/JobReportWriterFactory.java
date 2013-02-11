package zyake.apps.jenkinsjobexecutor.reports;

import zyake.apps.jenkinsjobexecutor.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobReportWriterFactory {

    protected static final Map<String, Class<JobReportWriter>> writerClasses = new ConcurrentHashMap<>();

    public static void configure(Map<String, String> reportWriters) {
         for ( Map.Entry<String, String> entry : reportWriters.entrySet() ) {
             boolean duplicatedKeyFound = writerClasses.containsKey(entry.getKey());
             if ( duplicatedKeyFound ) {
                 throw new ReportWriterException("duplicated key found: key=" + entry.getKey());
             }

             Class<JobReportWriter> writerClass = ClassUtils.loadClass(entry.getValue());
             boolean isNotWriter = ! ClassUtils.hasInterface(writerClass, JobReportWriter.class);
             if ( isNotWriter ) {
                 throw new ReportWriterException("class it not report writer: FQCN=" + writerClass);
             }

             writerClasses.put(entry.getKey(), writerClass);
         }
    }

    public static JobReportWriter newInstance(String reportWriter, Map<String, String> props) {
        boolean writerNotFound = ! writerClasses.containsKey(reportWriter);
        if ( writerNotFound ) {
            throw new ReportWriterException("writer not found: key=" + reportWriter);
        }

        Class<JobReportWriter> jobReportWriterClass = writerClasses.get(reportWriter);
        JobReportWriter jobReportWriter = ClassUtils.newInstance(jobReportWriterClass);
        jobReportWriter.initialize(props);

        return jobReportWriter;
    }
}
