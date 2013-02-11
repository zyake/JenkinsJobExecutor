package zyake.apps.jenkinsjobexecutor.config.rules;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import zyake.apps.jenkinsjobexecutor.config.Argument;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueCounter;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueValidator;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OptionalRuleTest {

    private ValueValidator emptyValidator;

    private ValueCounter emptyCounter;

    @Before
    public void before() {
        emptyValidator = mock(ValueValidator.class);
        emptyCounter = mock(ValueCounter.class);
        when(emptyCounter.checkCount(anyInt())).thenReturn(true);
    }

    @Test
    public void testCheckRule_normal_containsKey() throws Exception {
        Map<String, Argument> args = new HashMap<>();
        args.put("arg", new Argument("arg", "value"));
        ValidationContext context = new ValidationContext();

        OptionalRule target = new OptionalRule("arg",
                emptyValidator, emptyCounter);
        target.checkRule(context, args);

        assertThat(context.getResults().toString(), is("[]"));
    }

    @Test
    public void testCheckRule_normal_notContainsKey() throws Exception {
        Map<String, Argument> args = new HashMap<>();
        ValidationContext context = new ValidationContext();

        OptionalRule target = new OptionalRule("arg",
                emptyValidator, emptyCounter);
        target.checkRule(context, args);

        assertThat(context.getResults().toString(), is("[]"));
    }

    @Test
    public void testCheckRule_normal_notContainsKey_hasDefaultValues() throws Exception {
        Map<String, Argument> args = new HashMap<>();
        ValidationContext context = new ValidationContext();

        OptionalRule target = new OptionalRule("arg",
                emptyValidator, emptyCounter,
                "value1", "value2");
        target.checkRule(context, args);

        assertThat(args.toString(), is("{arg=key=arg, values=[value1, value2]}"));
    }

    @Test
    public void testCheckRule_error_containsKey() throws Exception {
        Map<String, Argument> args = new HashMap<>();
        args.put("arg", new Argument("arg", "value"));
        final ValidationContext context = new ValidationContext();
        ValueValidator validatorMock = mock(ValueValidator.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                context.addResult("ERROR", "INVALID VALUE");
                return null;
            }
        }).when(validatorMock).validateValue(context, "value");

        OptionalRule target = new OptionalRule("arg", validatorMock, emptyCounter);
        target.checkRule(context, args);

        assertThat(context.getResults().toString(), is("[rule=ERROR, description=INVALID VALUE]"));
    }
}
