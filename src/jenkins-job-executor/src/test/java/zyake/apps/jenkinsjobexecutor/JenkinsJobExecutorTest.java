package zyake.apps.jenkinsjobexecutor;

import org.junit.Test;


public class JenkinsJobExecutorTest {

    @Test
    public void testMain() throws Exception {
        JenkinsJobExecutor.main( new String[]{ "-url", "http://hogehoge:8080/jenkins", "-view", "trunk", "-name", "trunkAll" });
    }
}
