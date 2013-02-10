package zyake.apps.jenkinsjobexecutor.config;

import java.util.Map;

public interface ExecutorConfig {

    String getParser();

    Map<String, String> getRequestSenders();

    Map<String, String> getSerializers();

    Map<String, String> getReportWriters();

    Map<String, String> getLoaders();

    int getRetryCount();
}
