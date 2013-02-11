package zyake.apps.jenkinsjobexecutor.config;

import zyake.apps.jenkinsjobexecutor.config.rules.ParserRule;
import zyake.apps.jenkinsjobexecutor.senders.JobRequestSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentParserMock implements ArgumentParser {

    public Map<String, Argument> parse(String[] args, ParserRule rule) {
        Map<String, Argument> argMap = new HashMap<String, Argument>();
        argMap.put("url", new Argument("hoge", ""));
        argMap.put("sender", new Argument("sender", "null"));
        argMap.put("serializer", new Argument("serializer", "null"));
        argMap.put("output", new Argument("output", "stdout"));
        argMap.put("loader", new Argument("loader", "null"));

        return argMap;
    }
}
