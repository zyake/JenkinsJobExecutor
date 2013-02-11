package zyake.apps.jenkinsjobexecutor.config;

import java.util.HashMap;
import java.util.Map;

public class DefaultExecutorConfig implements ExecutorConfig {

    private int retryCount;

    private String parser;

    private Map<String, String> requestSenders = new HashMap<>();

    private Map<String, String> serializers  = new HashMap<>();

    private Map<String, String> reportWriters  = new HashMap<>();

    private Map<String, String> loaders  = new HashMap<>();

    @Override
    public String getParser() {
        return parser;
    }

    @Override
    public Map<String, String> getRequestSenders() {
        return requestSenders;
    }

    @Override
    public Map<String, String> getSerializers() {
        return serializers;
    }

    @Override
    public Map<String, String> getReportWriters() {
        return reportWriters;
    }

    @Override
    public Map<String, String> getLoaders() {
        return loaders;
    }

    @Override
    public int getRetryCount() {
        return retryCount;
    }

    public void setParser(String parser) {
        this.parser = parser;

    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public String toString() {
        return "parser=" + parser + ", retry count=" + retryCount +
                ", senders=" + requestSenders + ", serializers=" + serializers +
                ", writers=" + reportWriters + ", loaders=" + loaders;
    }
}
