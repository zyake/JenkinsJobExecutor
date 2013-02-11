package zyake.apps.jenkinsjobexecutor.util;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class ClassUtilsTest {

    @Test
    public void testHasInterface_normal_impl() throws Exception {
        boolean hasInterface = ClassUtils.hasInterface(HashMap.class, Map.class);
        assertTrue(hasInterface);
    }

    @Test
    public void testHasInterface_normal_notImpl() throws Exception {
        boolean hasInterface = ClassUtils.hasInterface(HashMap.class, Collection.class);
        assertFalse(hasInterface);
    }
}
