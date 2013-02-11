package zyake.apps.jenkinsjobexecutor.serializers;

import zyake.apps.jenkinsjobexecutor.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobSerializerFactory {

    protected static Map<String, Class<JobSerializer>> serializerClasses = new ConcurrentHashMap<>();

    public static void configure(Map<String, String> serializers) {
        for ( Map.Entry<String, String> entry : serializers.entrySet() ) {
            boolean duplicatedKeyFound = serializerClasses.containsKey(entry.getKey());
            if ( duplicatedKeyFound ) {
                throw new SerializerException("duplicated key found: key=" + entry.getKey());
            }

            Class<JobSerializer> serializerClass = ClassUtils.loadClass(entry.getValue());
            boolean isNotSerializer = ! ClassUtils.hasInterface(serializerClass, JobSerializer.class);
            if ( isNotSerializer ) {
                throw new SerializerException("class is not serializer: FQCN=" + serializerClass);
            }

            serializerClasses.put(entry.getKey(), serializerClass);
        }
    }

    public static JobSerializer newInstance(String serializer, Map<String, String> props) {
        boolean serializerNotFound = ! serializerClasses.containsKey(serializer);
        if ( serializerNotFound ) {
            throw new SerializerException("serializer not fond: key=" + serializer);
        }

        Class<JobSerializer> jobResultSerializerClass = serializerClasses.get(serializer);
        JobSerializer jobSerializer = ClassUtils.newInstance(jobResultSerializerClass);
        jobSerializer.initialize(props);

        return jobSerializer;
    }
}
