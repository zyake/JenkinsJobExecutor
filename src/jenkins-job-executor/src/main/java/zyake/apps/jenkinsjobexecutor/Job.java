package zyake.apps.jenkinsjobexecutor;

import java.util.ArrayList;
import java.util.List;

public class Job {

    private List<Job> dependencies = new ArrayList<>();

    private String name;

    private boolean completed = false;

    public Job(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public List<Job> getDependencies() {
        return dependencies;
    }

    public String getName() {
        return name;
    }

    public void setCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for ( Job job : dependencies ) {
            stringBuilder.append(job.getName() + ",");
        }
        boolean hasComma = stringBuilder.charAt(stringBuilder.length() - 1) == ',';
        if ( hasComma ) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return "name=" + getName() + ", completed=" + isCompleted() + ", dependencies=[" + stringBuilder.toString() + "]";
    }
}
