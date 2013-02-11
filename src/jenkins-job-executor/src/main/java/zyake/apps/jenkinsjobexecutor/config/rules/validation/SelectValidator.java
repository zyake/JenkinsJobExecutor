package zyake.apps.jenkinsjobexecutor.config.rules.validation;

import zyake.apps.jenkinsjobexecutor.config.rules.ValidationContext;

import java.util.HashSet;
import java.util.Set;

public class SelectValidator extends AbstractValidator {

    private Set<String> values = new HashSet<>();

    public SelectValidator(String... values) {
        for ( String value : values ) {
            this.values.add(value);
        }
    }

    @Override
    public void validateValue(ValidationContext context, String value) {
        boolean invalidValueFound = ! values.contains(value);
        if ( invalidValueFound ) {
            context.addResult("select", "invalid value found: value=" + value + ", selection=" + values);
        }
    }
}
