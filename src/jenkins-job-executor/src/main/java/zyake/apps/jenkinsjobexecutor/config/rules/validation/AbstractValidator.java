package zyake.apps.jenkinsjobexecutor.config.rules.validation;

public abstract class AbstractValidator implements ValueValidator {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
