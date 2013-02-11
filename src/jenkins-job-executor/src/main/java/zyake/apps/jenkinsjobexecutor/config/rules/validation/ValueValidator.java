package zyake.apps.jenkinsjobexecutor.config.rules.validation;

import zyake.apps.jenkinsjobexecutor.config.rules.ValidationContext;

public interface ValueValidator {

    void validateValue(ValidationContext context, String value);
}
