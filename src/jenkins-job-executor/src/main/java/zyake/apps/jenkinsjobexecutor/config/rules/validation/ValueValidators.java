package zyake.apps.jenkinsjobexecutor.config.rules.validation;


public enum ValueValidators {
    Any(new AnyValidator()), Url(new UrlValidator()),
    Regex(new RegexValidator()), NoValue(new NoValueValidator());

    private ValueValidator validator;

    private ValueValidators(ValueValidator validator) {
        this.validator = validator;
    }

    public ValueValidator getValidator() {
        return validator;
    }

    public static ValueValidator asSelection(String... selections) {
        return new SelectValidator(selections);
    }
}
