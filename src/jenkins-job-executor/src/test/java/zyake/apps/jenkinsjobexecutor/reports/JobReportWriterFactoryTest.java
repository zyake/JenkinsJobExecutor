package zyake.apps.jenkinsjobexecutor.reports;

import org.junit.Before;
import org.junit.Test;
import zyake.apps.jenkinsjobexecutor.Job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


public class JobReportWriterFactoryTest {

    @Before
    public void before() {
        JobReportWriterFactory.writerClasses.clear();
    }

    @Test
    public void testConfigure_normal() throws Exception {
        Map<String, String> writerMap = new HashMap<>();
        writerMap.put("writer", WriterMock.class.getName());

        JobReportWriterFactory.configure(writerMap);

        assertThat(JobReportWriterFactory.writerClasses.toString(),
                is("{writer=class zyake.apps.jenkinsjobexecutor.reports.JobReportWriterFactoryTest$WriterMock}"));
    }

    @Test
    public void testConfigure_normal_multiple() throws Exception {
        Map<String, String> writerMap = new HashMap<>();
        writerMap.put("writer", WriterMock.class.getName());
        writerMap.put("writer2", WriterMock2.class.getName());
        JobReportWriterFactory.configure(writerMap);

        Map<String, Class<JobReportWriter>> writerClasses = new TreeMap<>(JobReportWriterFactory.writerClasses);
        assertThat(writerClasses.toString(),
                is("{writer=class zyake.apps.jenkinsjobexecutor.reports.JobReportWriterFactoryTest$WriterMock, writer2=class zyake.apps.jenkinsjobexecutor.reports.JobReportWriterFactoryTest$WriterMock2}"));
    }

    @Test(expected=ReportWriterException.class)
    public void testConfigure_error_duplicatedKey() throws Exception {
        Map<String, String> writerMap = new HashMap<>();
        writerMap.put("writer", WriterMock.class.getName());

        JobReportWriterFactory.configure(writerMap);
        JobReportWriterFactory.configure(writerMap);
    }

    @Test(expected=ReportWriterException.class)
    public void testConfigure_error_invalidFQCN() throws Exception {
        Map<String, String> writerMap = new HashMap<>();
        writerMap.put("writer", Object.class.getName());

        JobReportWriterFactory.configure(writerMap);
    }

    @Test
    public void testNewInstance_normal() throws Exception {
        Map<String, String> writerMap = new HashMap<>();
        writerMap.put("writer", WriterMock.class.getName());
        JobReportWriterFactory.configure(writerMap);

        JobReportWriter writer = JobReportWriterFactory.newInstance("writer", new HashMap<String, String>());

        assertNotNull(writer);
    }

    @Test(expected=ReportWriterException.class)
    public void testNewInstance_error_keyNotFound() {
        JobReportWriterFactory.newInstance("INVALID", new HashMap<String, String>());
    }

    public static class WriterMock implements JobReportWriter {
        @Override
        public void initialize(Map<String, String> props) {
        }

        @Override
        public void writeJobs(List<Job> jobs) {
        }
        @Override
        public void finish() {
        }
    }

    public static class WriterMock2 implements JobReportWriter {
        @Override
        public void initialize(Map<String, String> props) {
        }

        @Override
        public void writeJobs(List<Job> jobs) {
        }
        @Override
        public void finish() {
        }
    }
}
