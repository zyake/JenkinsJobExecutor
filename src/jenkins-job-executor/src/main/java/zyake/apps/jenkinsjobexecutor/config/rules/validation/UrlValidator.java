package zyake.apps.jenkinsjobexecutor.config.rules.validation;

import zyake.apps.jenkinsjobexecutor.config.rules.ValidationContext;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlValidator extends AbstractValidator {

    @Override
    public void validateValue(ValidationContext context, String value) {
        try {
            new URL(value);
        } catch (MalformedURLException e) {
            context.addResult("url", "value is not url: value=" + value);
        }
    }
}
