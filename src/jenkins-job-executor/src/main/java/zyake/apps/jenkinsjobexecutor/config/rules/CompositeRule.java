package zyake.apps.jenkinsjobexecutor.config.rules;

import zyake.apps.jenkinsjobexecutor.config.Argument;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompositeRule implements ParserRule {

    private List<ParserRule> rules;

    public CompositeRule(List<ParserRule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    @Override
    public void checkRule(ValidationContext context, Map<String, Argument> args) {
        for ( ParserRule rule : rules ) {
            rule.checkRule(context, args);
        }
    }

    @Override
    public String toString() {
        return "type=composite, rules=" + rules;
    }
}
