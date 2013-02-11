package zyake.apps.jenkinsjobexecutor.config.rules;

import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueCounts;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueValidators;

import java.util.ArrayList;
import java.util.List;

public class ParserRuleBuilder {

    protected List<ParserRule> rules = new ArrayList<>();

    public ParserRuleBuilder require(String arg, ValueCounts counts, ValueValidators validators) {
        rules.add(new RequireRule(arg, validators.getValidator(), counts.getCounter()));

        return this;
    }

    public ParserRuleBuilder optional(String arg, ValueCounts counts, ValueValidators validators) {
        rules.add(new OptionalRule(arg, validators.getValidator(), counts.getCounter()));

        return this;
    }

    public ParserRuleBuilder optional(String arg, ValueCounts counts, ValueValidators validators, String... defaultValues) {
        rules.add(new OptionalRule(arg, validators.getValidator(), counts.getCounter(), defaultValues));

        return this;
    }

    public ParserRuleBuilder select(String... args) {
        rules.add(new SelectRule(args));

        return this;
    }

    public ParserRule build() {
        return new CompositeRule(rules);
    }
}
