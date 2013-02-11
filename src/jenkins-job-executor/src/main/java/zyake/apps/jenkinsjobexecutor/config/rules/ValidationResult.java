package zyake.apps.jenkinsjobexecutor.config.rules;

public class ValidationResult {

    private String rule;

    private String description;

    public ValidationResult(String rule, String description) {
        this.rule = rule;
        this.description = description;
    }

    public String getRule() {
        return rule;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "rule=" + rule + ", description=" + description;
    }
}
