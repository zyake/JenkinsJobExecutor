package zyake.apps.jenkinsjobexecutor.config.rules;

import java.util.ArrayList;
import java.util.List;

public class ValidationContext {

    private List<ValidationResult> results = new ArrayList<>();

    public void addResult(String rule, String description) {
        results.add(new ValidationResult(rule, description));
    }

    public List<ValidationResult> getResults() {
        return results;
    }

    public boolean validationFailed() {
        return results.size() > 0;
    }
}
