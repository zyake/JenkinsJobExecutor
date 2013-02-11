package zyake.apps.jenkinsjobexecutor.serializers;

import org.junit.Before;
import org.junit.Test;
import zyake.apps.jenkinsjobexecutor.Job;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class JobSerializerFactoryTest {

    @Before
    public void before() {
        JobSerializerFactory.serializerClasses.clear();
    }

    @Test
    public void testConfigure_normal() throws Exception {
        Map<String, String> serializerMap = new HashMap<>();
        serializerMap.put("serializer", SerializerMock.class.getName());

        JobSerializerFactory.configure(serializerMap);

        assertThat(JobSerializerFactory.serializerClasses.toString(),
                is("{serializer=class zyake.apps.jenkinsjobexecutor.serializers.JobSerializerFactoryTest$SerializerMock}"));
    }

    @Test
    public void testConfigure_normal_multiple() throws Exception {
        Map<String, String> serializerMap = new HashMap<>();
        serializerMap.put("serializer", SerializerMock.class.getName());
        serializerMap.put("serializer2", SerializerMock2.class.getName());

        JobSerializerFactory.configure(serializerMap);

        Map<String, Class<JobSerializer>> serializerClasses = new TreeMap<>(JobSerializerFactory.serializerClasses);
        assertThat(serializerClasses.toString(),
                is("{serializer=class zyake.apps.jenkinsjobexecutor.serializers.JobSerializerFactoryTest$SerializerMock, " +
                    "serializer2=class zyake.apps.jenkinsjobexecutor.serializers.JobSerializerFactoryTest$SerializerMock2}"));
    }

    @Test(expected=SerializerException.class)
    public void testConfigure_error_duplicatedKey() {
        Map<String, String> serializerMap = new HashMap<>();
        serializerMap.put("serializer", SerializerMock.class.getName());

        JobSerializerFactory.configure(serializerMap);
        JobSerializerFactory.configure(serializerMap);
    }

    @Test(expected=SerializerException.class)
    public void testConfigure_error_invalidFQCN() {
        Map<String, String> serializerMap = new HashMap<>();
        serializerMap.put("serializer", Object.class.getName());

        JobSerializerFactory.configure(serializerMap);
    }

    @Test
    public void testNewInstance_normal() throws Exception {
        Map<String, String> serializerMap = new HashMap<>();
        serializerMap.put("serializer", SerializerMock.class.getName());
        JobSerializerFactory.configure(serializerMap);

        JobSerializer serializer = JobSerializerFactory.newInstance("serializer", new HashMap<String, String>());
        assertNotNull(serializer);
    }

    @Test(expected=SerializerException.class)
    public void testNewInstance_error_keyNotFound() throws Exception {
        JobSerializerFactory.newInstance("serializer", new HashMap<String, String>());
    }

    public static class SerializerMock implements JobSerializer {
        @Override
        public void initialize(Map<String, String> props) {
        }
        @Override
        public void serializeJob(Job job) {
        }
        @Override
        public void finish() {
        }
    }

    public static class SerializerMock2 implements JobSerializer {
        @Override
        public void initialize(Map<String, String> props) {
        }
        @Override
        public void serializeJob(Job job) {
        }
        @Override
        public void finish() {
        }
    }
}
