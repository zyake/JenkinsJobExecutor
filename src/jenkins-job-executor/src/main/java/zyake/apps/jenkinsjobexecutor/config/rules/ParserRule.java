package zyake.apps.jenkinsjobexecutor.config.rules;

import zyake.apps.jenkinsjobexecutor.config.Argument;

import java.util.Map;

public interface ParserRule {

    void checkRule(ValidationContext context, Map<String, Argument> args);
}
