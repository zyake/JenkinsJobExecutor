package zyake.apps.jenkinsjobexecutor.config.rules.validation;

import zyake.apps.jenkinsjobexecutor.config.rules.ValidationContext;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexValidator extends AbstractValidator {

    @Override
    public void validateValue(ValidationContext context, String value) {
        try {
            Pattern.compile(value);
        } catch (PatternSyntaxException e) {
            context.addResult("regex" , "value is not regular expression: value=" + value);
        }
    }
}
