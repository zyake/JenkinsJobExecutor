package zyake.apps.jenkinsjobexecutor.config.rules;

import zyake.apps.jenkinsjobexecutor.config.Argument;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueCounter;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueValidator;

import java.util.Map;

public class RequireRule extends AbstractRule {

    private String key;

    public RequireRule(String key, ValueValidator validator, ValueCounter counter) {
        super(validator, counter);
        this.key = key;
    }

    @Override
    public void checkRule(ValidationContext context, Map<String, Argument> args) {
        boolean argNotFound = ! args.containsKey(key);
        if ( argNotFound ) {
            context.addResult("require arg", "arguments not found: key=" + key);
            return;
        }
        doValidate(key, context, args);
    }

    @Override
    public String toString() {
        return "type=require, key=" + key + ", validator=" + validator + ", counter=" + counter;
    }
}
