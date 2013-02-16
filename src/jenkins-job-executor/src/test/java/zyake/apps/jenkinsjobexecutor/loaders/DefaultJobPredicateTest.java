package zyake.apps.jenkinsjobexecutor.loaders;

import org.junit.Test;
import zyake.apps.jenkinsjobexecutor.Job;
import zyake.apps.jenkinsjobexecutor.util.Predicate;

import static junit.framework.Assert.assertEquals;


public class DefaultJobPredicateTest {

    @Test
    public void testPredicate_normal_noFilter() throws Exception {
        Job targetJob = new Job("hoge");
        boolean isAccepted = new DefaultJobPredicate().evaluate(targetJob);

        assertEquals(isAccepted, true);
    }

    @Test
    public void testPredicate_normal_exclude() throws Exception {
        Job targetJob = new Job("hoge");
        Predicate<Job> target = new DefaultJobPredicate().addExcludePatterns("hoge");
        boolean isAccepted = target.evaluate(targetJob);

        assertEquals(isAccepted, false);
    }

    @Test
    public void testPredicate_normal_includeAndExclude() throws Exception {
        Job targetJob = new Job("hoge");
        Predicate<Job> target = new DefaultJobPredicate()
                .addExcludePatterns("hoge")
                .addIncludePatterns("hoge");
        boolean isAccepted = target.evaluate(targetJob);

        assertEquals(isAccepted, true);
    }

    @Test
    public void testPredicate_normal_targetAndExclude() throws Exception {
        Job targetJob = new Job("hoge");
        Predicate<Job> target = new DefaultJobPredicate()
                .addExcludePatterns("hoge")
                .addTargetNames("hoge");
        boolean isAccepted = target.evaluate(targetJob);

        assertEquals(isAccepted, true);
    }
}
