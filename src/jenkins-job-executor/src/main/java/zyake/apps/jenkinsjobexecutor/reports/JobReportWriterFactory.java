package zyake.apps.jenkinsjobexecutor.reports;

/**
 * Created with IntelliJ IDEA.
 * User: zyake
 * Date: 13/02/10
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public class JobReportWriterFactory {
    public static void configure(Object reportWriters) {
    }

    public static JobReportWriter newInstance(String reportWriter) {
        return new JobReportWriter();
    }
}
