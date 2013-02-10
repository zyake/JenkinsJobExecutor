package zyake.apps.jenkinsjobexecutor.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigLoader {

    public static ExecutorConfig loadCofig(String configPath) {
        InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(configPath);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            new ConfigException("load config failed. path=" + configPath, e);
        }

        DefaultExecutorConfig config = new DefaultExecutorConfig();
        Enumeration<?> names = properties.propertyNames();
        while ( names.hasMoreElements() ) {
            String name = names.nextElement().toString();
            String property = properties.getProperty(name.toString());
            addConfig(config, name, property);
        }

        validate(config);

        return config;
    }

    private static void validate(DefaultExecutorConfig config) {
    }

    private static void addConfig(DefaultExecutorConfig config, String name, String property) {
        boolean isRetryCount = "executor.retrycount".equals(name);
        if ( isRetryCount ) {
            config.setRetryCount(Integer.parseInt(property));
            return;
        }

        boolean isArgParser = "executor.argumentparser".equals(name);
        if ( isArgParser ) {
            config.setParser(property);
            return;
        }

        boolean isRequestSender = name.startsWith("executor.requestsenders.");
        if ( isRequestSender ) {
            String senderName = name.substring("executor.requestsenders.".length());
            config.getRequestSenders().put(senderName, property);
            return;
        }

        boolean isSerializer = name.startsWith("executor.serializers.");
        if ( isSerializer ) {
            String serializerName = name.substring("executor.serializers.".length());
            config.getSerializers().put(serializerName, property);
            return;
        }

        boolean isWriter = name.startsWith("executor.reportwriters.");
        if ( isWriter ) {
            String writerName = name.substring("executor.reportwriters.".length());
            config.getReportWriters().put(writerName, property);
            return;
        }

        boolean isLoader = name.startsWith("executor.loaders.");
        if ( isLoader ) {
            String loaderName = name.substring("executor.loaders.".length());
            config.getLoaders().put(loaderName, property);
        }
    }
}
