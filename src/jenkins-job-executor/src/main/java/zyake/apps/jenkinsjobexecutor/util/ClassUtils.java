package zyake.apps.jenkinsjobexecutor.util;

import com.sun.xml.internal.ws.util.UtilException;
import zyake.apps.jenkinsjobexecutor.config.ArgumentParser;

public class ClassUtils {

    public static <T> T newInstance(String fqcn) {
        try {
            Class<?> aClass = Class.forName(fqcn);
            return (T) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            throw new UtilException("FQCN not found: " + e);
        } catch (InstantiationException e) {
            throw new UtilException("initialization failed: " + e);
        } catch (IllegalAccessException e) {
            throw new UtilException("constructor access failed: " + e);
        }
    }
}
