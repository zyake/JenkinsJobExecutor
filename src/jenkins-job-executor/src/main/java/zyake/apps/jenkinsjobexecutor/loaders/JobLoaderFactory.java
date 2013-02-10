package zyake.apps.jenkinsjobexecutor.loaders;

/**
 * Created with IntelliJ IDEA.
 * User: zyake
 * Date: 13/02/10
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public class JobLoaderFactory {
    public static void configure(Object loaders) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public static JobLoader newInstance(String jobLoader) {
        return new JobLoader();
    }
}
