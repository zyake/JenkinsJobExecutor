package zyake.apps.jenkinsjobexecutor.config.rules;

import org.junit.Before;
import org.junit.Test;
import zyake.apps.jenkinsjobexecutor.config.Argument;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueCounter;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueValidator;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequireRuleTest {

    private ValueValidator emptyValidator;

    private ValueCounter emptyCounter;

    @Before
    public void before() {
        emptyValidator = mock(ValueValidator.class);
        emptyCounter = mock(ValueCounter.class);
        when(emptyCounter.checkCount(anyInt())).thenReturn(true);
    }

    @Test
    public void testCheckRule_normal_containsKey() {
        Map<String, Argument> argMap = new HashMap<>();
        argMap.put("arg", new Argument("arg", "value"));
        ValidationContext context = new ValidationContext();

        RequireRule target = new RequireRule("arg", emptyValidator, emptyCounter);
        target.checkRule(context, argMap);

        assertThat(context.getResults().toString(), is("[]"));
    }

    @Test
    public void testCheckRule_error_noContainsKey() {
        Map<String, Argument> argMap = new HashMap<>();
        ValidationContext context = new ValidationContext();

        RequireRule target = new RequireRule("arg", emptyValidator, emptyCounter);
        target.checkRule(context, argMap);

        assertThat(context.getResults().toString(), is("[rule=require arg, description=arguments not found: key=arg]"));
    }
}
