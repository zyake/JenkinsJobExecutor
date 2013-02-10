package zyake.apps.jenkinsjobexecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zyake
 * Date: 13/02/10
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class Job {

    private List<Job> dependencies = new ArrayList<>();

    private String name;

    public boolean isCompleted() {
        return true;
    }

    public List<Job> getDependencies() {
        return dependencies;
    }

    public String getName() {
        return name;
    }

    public void setCompleted() {

    }
}
