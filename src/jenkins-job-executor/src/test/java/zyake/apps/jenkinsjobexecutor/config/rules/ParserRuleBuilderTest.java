package zyake.apps.jenkinsjobexecutor.config.rules;

import org.junit.Test;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueCounts;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueValidators;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParserRuleBuilderTest {

    @Test
    public void testRequire_normal() throws Exception {
        ParserRuleBuilder target = new ParserRuleBuilder();
        target.require("key", ValueCounts.Once, ValueValidators.Any);
        assertThat(target.rules.toString(), is("[type=require, key=key, validator=AnyValidator, counter=1]"));
    }

    @Test
    public void testOptional_normal_noDefaultArg() throws Exception {
        ParserRuleBuilder target = new ParserRuleBuilder();
        target.optional("key", ValueCounts.Once, ValueValidators.Any);
        assertThat(target.rules.toString(),
                is("[type=optional, key=key, validator=AnyValidator, counter=1, default arg=null]"));
    }

    @Test
    public void testOptional_normal_hasDefaultArg() throws Exception {
        ParserRuleBuilder target = new ParserRuleBuilder();
        target.optional("key", ValueCounts.Once, ValueValidators.Any, "value1", "value2");
        assertThat(target.rules.toString(),
                is("[type=optional, key=key, validator=AnyValidator, counter=1, default arg=key=key, values=[value1, value2]]"));
    }

    @Test
    public void testSelect_normal() throws Exception {
        ParserRuleBuilder target = new ParserRuleBuilder();
        target.select("arg1", "arg2", "arg3");
        assertThat(target.rules.toString(),
                is("[type=selection, selections=[arg1, arg2, arg3]]"));
    }

    @Test
    public void testBuild_normal_noRule() throws Exception {
        ParserRule rule = new ParserRuleBuilder().build();
        assertThat(rule.toString(), is("type=composite, rules=[]"));
    }

    @Test
    public void testBuild_normal_requireTwo() throws Exception {
        ParserRule rule = new ParserRuleBuilder()
                .require("arg1", ValueCounts.Many, ValueValidators.Regex)
                .require("arg2", ValueCounts.Once, ValueValidators.Url)
                .build();

        assertThat(rule.toString(),
                is("type=composite, rules=[type=require, key=arg1, validator=RegexValidator, counter=more than one, type=require, key=arg2, validator=UrlValidator, counter=1]"));
    }
}
