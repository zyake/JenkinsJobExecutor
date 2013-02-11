package zyake.apps.jenkinsjobexecutor.loaders;

import zyake.apps.jenkinsjobexecutor.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobLoaderFactory {

    protected static final Map<String, Class<JobLoader>> loaderClasses = new ConcurrentHashMap<>();

    public static void configure(Map<String, String> loaders) {
        for ( Map.Entry<String, String> entry : loaders.entrySet() ) {
            boolean duplicatedKeyFound = loaderClasses.containsKey(entry.getKey());
            if ( duplicatedKeyFound ) {
                throw new LoaderException("duplicated key found: key=" + entry.getKey());
            }

            Class<JobLoader> loaderClass = ClassUtils.loadClass(entry.getValue());
            boolean isNotLoader = ! ClassUtils.hasInterface(loaderClass, JobLoader.class);
            if ( isNotLoader ) {
                throw new LoaderException("class is not loader: FQCN=" + loaderClass);
            }

            loaderClasses.put(entry.getKey(), loaderClass);
        }
    }

    public static JobLoader newInstance(String loader, Map<String, String> props) {
        boolean keyNotFound = ! loaderClasses.containsKey(loader);
        if ( keyNotFound ) {
            throw new LoaderException("key not found=" + loader);
        }

        Class<JobLoader> jobLoaderClass = loaderClasses.get(loader);
        JobLoader jobLoader = ClassUtils.newInstance(jobLoaderClass);
        jobLoader.initialize(props);

        return jobLoader;
    }
}
