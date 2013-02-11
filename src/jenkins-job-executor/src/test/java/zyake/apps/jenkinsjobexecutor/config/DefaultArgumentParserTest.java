package zyake.apps.jenkinsjobexecutor.config;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DefaultArgumentParserTest {

    @Test
    public void testDoParse_normal_singleKeyOnly() throws Exception {
        String[] args = { "-key" };
        Map<String,Argument> argumentMap = new DefaultArgumentParser().doParse(args);
        assertThat(new TreeMap<>(argumentMap).toString(),
                is("{key=key=key, values=[]}"));
    }

    @Test
    public void testDoParse_normal_singleKeySingleValue() throws Exception {
        String[] args = { "-key" , "value" };
        Map<String,Argument> argumentMap = new DefaultArgumentParser().doParse(args);
        assertThat(new TreeMap<>(argumentMap).toString(),
                is("{key=key=key, values=[value]}"));
    }

    @Test
    public void testDoParse_normal_singleKeyMultiValue() throws Exception {
        String[] args = { "-key" , "value1", "value2" };
        Map<String,Argument> argumentMap = new DefaultArgumentParser().doParse(args);
        assertThat(new TreeMap<>(argumentMap).toString(),
                is("{key=key=key, values=[value1, value2]}"));
    }

    @Test
    public void testDoParse_normal_multiKeyNoValue() throws Exception {
        String[] args = { "-key1" , "-key2" };
        Map<String,Argument> argumentMap = new DefaultArgumentParser().doParse(args);
        assertThat(new TreeMap<>(argumentMap).toString(),
                is("{key1=key=key1, values=[], key2=key=key2, values=[]}"));
    }

    @Test
    public void testDoParse_normal_multiKeyNoValueAndHasValue() throws Exception {
        String[] args = { "-key1" , "-key2", "value" };
        Map<String,Argument> argumentMap = new DefaultArgumentParser().doParse(args);
        assertThat(new TreeMap<>(argumentMap).toString(),
                is("{key1=key=key1, values=[], key2=key=key2, values=[value]}"));
    }

    @Test
    public void testDoParse_normal_multiKeyHasValueAndHasValue() throws Exception {
        String[] args = { "-key1" , "value1", "-key2", "value2" };
        Map<String,Argument> argumentMap = new DefaultArgumentParser().doParse(args);
        assertThat(new TreeMap<>(argumentMap).toString(),
                is("{key1=key=key1, values=[value1], key2=key=key2, values=[value2]}"));
    }

    @Test
    public void testDoParse_normal_multiKeyHasMultiValueAndHasMultiValue() throws Exception {
        String[] args = { "-key1" , "value11", "value12", "-key2", "value21", "value22" };
        Map<String,Argument> argumentMap = new DefaultArgumentParser().doParse(args);
        assertThat(new TreeMap<>(argumentMap).toString(),
                is("{key1=key=key1, values=[value11, value12], key2=key=key2, values=[value21, value22]}"));
    }
}

