package zyake.apps.jenkinsjobexecutor.config.rules;

import zyake.apps.jenkinsjobexecutor.config.Argument;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueCounter;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueCounts;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueValidator;

import java.util.Map;

public abstract class AbstractRule implements ParserRule {

    protected ValueValidator validator;

    protected ValueCounter counter;

    public AbstractRule(ValueValidator validator, ValueCounter counter) {
        this.validator = validator;
        this.counter = counter;
    }

    protected void doValidate(String key, ValidationContext context, Map<String, Argument> args) {
        Argument argument = args.get(key);
        int valueSize = argument.getValues().size();
        boolean countNotMatched = !this.counter.checkCount(valueSize);
        if ( countNotMatched ) {
            context.addResult("value count",
                    "value Count not matched: expects=" + this.counter + ", actual=" + valueSize);
        }

        for ( String value : argument.getValues() ) {
            validator.validateValue(context, value);
        }
    }
}