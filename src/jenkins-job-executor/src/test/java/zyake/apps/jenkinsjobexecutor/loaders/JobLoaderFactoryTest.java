package zyake.apps.jenkinsjobexecutor.loaders;

import org.junit.Before;
import org.junit.Test;
import zyake.apps.jenkinsjobexecutor.Job;
import zyake.apps.jenkinsjobexecutor.util.Predicate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class JobLoaderFactoryTest {

    @Before
    public void before() {
        JobLoaderFactory.loaderClasses.clear();
    }

    @Test
    public void testConfigure_normal() throws Exception {
        Map<String, String> loaderMap = new HashMap<>();
        loaderMap.put("loader", LoaderMock.class.getName());

        JobLoaderFactory.configure(loaderMap);

        Map<String, Class<JobLoader>> loaderClasses = new TreeMap<>(JobLoaderFactory.loaderClasses);
        assertThat(loaderClasses.toString(), is("{loader=class zyake.apps.jenkinsjobexecutor.loaders.JobLoaderFactoryTest$LoaderMock}"));
    }

    @Test
    public void testConfigure_normal_multiple() throws Exception {
        Map<String, String> loaderMap = new HashMap<>();
        loaderMap.put("loader", LoaderMock.class.getName());
        loaderMap.put("loader2", LoaderMock2.class.getName());

        JobLoaderFactory.configure(loaderMap);

        Map<String, Class<JobLoader>> loaderClasses = new TreeMap<>(JobLoaderFactory.loaderClasses);
        assertThat(loaderClasses.toString(),
                is("{loader=class zyake.apps.jenkinsjobexecutor.loaders.JobLoaderFactoryTest$LoaderMock, " +
                "loader2=class zyake.apps.jenkinsjobexecutor.loaders.JobLoaderFactoryTest$LoaderMock2}"));
    }

    @Test(expected=LoaderException.class)
    public void testConfigure_error_duplicatedKey() throws Exception {
        Map<String, String> loaderMap = new HashMap<>();
        loaderMap.put("loader", LoaderMock.class.getName());

        JobLoaderFactory.configure(loaderMap);
        JobLoaderFactory.configure(loaderMap);
 }

    @Test(expected=LoaderException.class)
    public void testConfigure_error_invalidFQCN() throws Exception {
        Map<String, String> loaderMap = new HashMap<>();
        loaderMap.put("loader", Object.class.getName());

        JobLoaderFactory.configure(loaderMap);
    }

    @Test
    public void testNewInstance_normal() throws Exception {
        Map<String, String> loaderMap = new HashMap<>();
        loaderMap.put("loader", LoaderMock.class.getName());
        JobLoaderFactory.configure(loaderMap);

        JobLoader jobLoader = JobLoaderFactory.newInstance("loader", new HashMap<String, String>());

        assertNotNull(jobLoader);
    }

    @Test(expected=LoaderException.class)
    public void testNewInstance_error_keyNotFound() throws Exception {
        JobLoaderFactory.newInstance("loader", new HashMap<String, String>());
    }

    public static class LoaderMock implements JobLoader {
        @Override
        public List<Job> loadJobs(String url) {
            return null;
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

    public static class LoaderMock2 implements JobLoader {
        @Override
        public List<Job> loadJobs(String url) {
            return null;
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
}
