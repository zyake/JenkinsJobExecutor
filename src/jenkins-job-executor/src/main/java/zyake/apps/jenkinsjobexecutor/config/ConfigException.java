package zyake.apps.jenkinsjobexecutor.config;

import java.io.IOException;

public class ConfigException extends RuntimeException {
    public ConfigException(String s, IOException e) {
        super(s, e);
    }

    public ConfigException(String msg) {
        super(msg);
    }
}
