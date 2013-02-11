package zyake.apps.jenkinsjobexecutor.senders;

import org.junit.Before;
import org.junit.Test;
import zyake.apps.jenkinsjobexecutor.Job;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


public class JobRequestSenderFactoryTest {

    @Before
    public void before() {
        JobRequestSenderFactory.senderClasses.clear();
    }

    @Test
    public void testConfigure_normal() throws Exception {
        Map<String, String> senderMap = new HashMap<>();
        senderMap.put("sender", SenderMock.class.getName());

        JobRequestSenderFactory.configure(senderMap);

        assertThat(JobRequestSenderFactory.senderClasses.toString(),
                is("{sender=class zyake.apps.jenkinsjobexecutor.senders.JobRequestSenderFactoryTest$SenderMock}"));
    }

    @Test
    public void testConfigure_normal_multiple() throws Exception {
        Map<String, String> senderMap = new HashMap<>();
        senderMap.put("sender", SenderMock.class.getName());
        senderMap.put("sender2", SenderMock2.class.getName());

        JobRequestSenderFactory.configure(senderMap);

        Map<String, Class<JobRequestSender>> senderClasses = new TreeMap<>(JobRequestSenderFactory.senderClasses);

        assertThat(senderClasses.toString(),
                is("{sender=class zyake.apps.jenkinsjobexecutor.senders.JobRequestSenderFactoryTest$SenderMock, " +
                   "sender2=class zyake.apps.jenkinsjobexecutor.senders.JobRequestSenderFactoryTest$SenderMock2}"));
    }

    @Test(expected=SenderException.class)
    public void testConfigure_error_duplicatedKey() {
        Map<String, String> senderMap = new HashMap<>();
        senderMap.put("sender", SenderMock.class.getName());

        JobRequestSenderFactory.configure(senderMap);
        JobRequestSenderFactory.configure(senderMap);
    }

    @Test(expected=SenderException.class)
    public void testConfigure_error_invalidFQCN() {
        Map<String, String> senderMap = new HashMap<>();
        senderMap.put("sender", Object.class.getName());

        JobRequestSenderFactory.configure(senderMap);
    }

    @Test
    public void testNewInstance_normal() throws Exception {
        Map<String, String> senderMap = new HashMap<>();
        senderMap.put("sender", SenderMock.class.getName());
        JobRequestSenderFactory.configure(senderMap);

        JobRequestSender sender = JobRequestSenderFactory.newInstance("sender", new HashMap<String, String>());
        assertNotNull(sender);
    }

    @Test(expected=SenderException.class)
    public void testNewInstance_error_keyNotFound() throws Exception {
        JobRequestSenderFactory.newInstance("sender", new HashMap<String, String>());
    }

    public static class SenderMock implements JobRequestSender {
        @Override
        public void initialize(Map<String, String> props) {
        }
        @Override
        public void disableJob(Job job) {
        }
        @Override
        public void enableJob(Job job) {
        }
        @Override
        public void executeJob(Job job) {
        }
        @Override
        public void close() throws Exception {
        }
    }

    public static class SenderMock2 implements JobRequestSender {
        @Override
        public void initialize(Map<String, String> props) {
        }
        @Override
        public void disableJob(Job job) {
        }
        @Override
        public void enableJob(Job job) {
        }
        @Override
        public void executeJob(Job job) {
        }
        @Override
        public void close() throws Exception {
        }
    }
}
