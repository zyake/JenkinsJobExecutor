package zyake.apps.jenkinsjobexecutor.serializers;

/**
 * Created with IntelliJ IDEA.
 * User: zyake
 * Date: 13/02/10
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public class JobResultSerializerFactory {
    public static void configure(Object serializers) {
    }

    public static JobResultSerializer newInstance(String serializer) {
        return new JobResultSerializer();
    }
}
