package zyake.apps.jenkinsjobexecutor.config.rules;

import org.junit.Test;
import zyake.apps.jenkinsjobexecutor.config.Argument;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SelectRuleTest {

    @Test
    public void testCheckRule_normal_contains() throws Exception {
        Map<String, Argument> argMap = new HashMap<>();
        argMap.put("arg", new Argument("arg", "value"));
        ValidationContext context = new ValidationContext();

        SelectRule target = new SelectRule("arg", "arg2", "arg3");
        target.checkRule(context, argMap);

        assertThat(context.getResults().toString(), is("[]"));
    }

    @Test
    public void testCheckRule_error_notContains() throws Exception {
        Map<String, Argument> argMap = new HashMap<>();
        ValidationContext context = new ValidationContext();

        SelectRule target = new SelectRule("arg1", "arg2", "arg3");
        target.checkRule(context, argMap);

        assertThat(context.getResults().toString(),
                is("[rule=selection arg, description=selection not found; selections=[arg1, arg2, arg3]]"));
    }

    @Test
    public void testCheckRule_error_multipleContains() throws Exception {
        Map<String, Argument> argMap = new HashMap<>();
        argMap.put("arg1", new Argument("arg1", "value1"));
        argMap.put("arg2", new Argument("arg2", "value2"));
        ValidationContext context = new ValidationContext();

        SelectRule target = new SelectRule("arg1", "arg2", "arg3");
        target.checkRule(context, argMap);

        assertThat(context.getResults().toString(),
                is("[rule=selection arg, description=multiple selection specified: selections=[arg1, arg2, arg3], specified selections=[arg1, arg2]]"));
    }
}
