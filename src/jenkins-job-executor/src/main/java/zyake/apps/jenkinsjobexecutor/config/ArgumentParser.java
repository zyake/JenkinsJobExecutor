package zyake.apps.jenkinsjobexecutor.config;

import zyake.apps.jenkinsjobexecutor.senders.JobRequestSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentParser {

    public Map<String, Argument> parse(String[] args) {
        Map<String, Argument> argMap = new HashMap<String, Argument>();
        argMap.put("url", new Argument("hoge"));
        argMap.put("sender", new Argument("null"));
        argMap.put("serializer", new Argument("null"));
        argMap.put("output", new Argument("stdout"));
        argMap.put("loader", new Argument("null"));

        return argMap;
    }
}
