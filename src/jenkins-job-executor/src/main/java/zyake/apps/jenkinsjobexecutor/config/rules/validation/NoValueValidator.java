package zyake.apps.jenkinsjobexecutor.config.rules.validation;

import zyake.apps.jenkinsjobexecutor.config.rules.ValidationContext;

public class NoValueValidator extends AbstractValidator {

    @Override
    public void validateValue(ValidationContext context, String value) {
        context.addResult("no value", "value found: value=" + value);
    }
}
