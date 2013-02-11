package zyake.apps.jenkinsjobexecutor.util;

import zyake.apps.jenkinsjobexecutor.senders.JobRequestSender;

public class ClassUtils {

    public static <T> T newInstance(String fqcn) {
        try {
            Class<?> aClass = Class.forName(fqcn);
            return (T) aClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new UtilException("instantination failed: FQCN=" + fqcn, e);
        }
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            T newInstance = clazz.newInstance();
            return newInstance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new UtilException("instantination failed", e);
        }
    }

    public static <T> Class<T> loadClass(String fqcn) {
        try {
            return (Class<T>) Class.forName(fqcn);
        } catch (ClassNotFoundException e) {
            throw new UtilException("class not found: FQCN=" + fqcn, e);
        }
    }

    public static boolean hasInterface(Class target, Class interfaceClazz) {
        boolean isNotInterface = ! interfaceClazz.isInterface();
        if ( isNotInterface ) {
            throw new UtilException(interfaceClazz.getName() + " is not interface");
        }

        Class currentClass = target;
        while ( currentClass != null ) {
            for ( Class specifiedInterface : currentClass.getInterfaces() ) {
                boolean interfaceMatched = specifiedInterface == interfaceClazz;
                if ( interfaceMatched ) {
                    return true;
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return false;
    }

}
