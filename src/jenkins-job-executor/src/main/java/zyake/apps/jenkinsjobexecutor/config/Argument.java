package zyake.apps.jenkinsjobexecutor.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Argument {

    private String key;

    private String value;

    private List<String> values;

    public Argument(String key, String value) {
        this.key = key;
        this.value = value;
        this.values = Collections.unmodifiableList(Arrays.asList(value));
    }

    public Argument(String key, List<String> values) {
        this.key = key;
        this.values = Collections.unmodifiableList(new ArrayList<>(values));
    }

    public boolean isSingleValue() {
        return values.size() == 1;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "key=" + key + ", values=" + values;
    }
}
