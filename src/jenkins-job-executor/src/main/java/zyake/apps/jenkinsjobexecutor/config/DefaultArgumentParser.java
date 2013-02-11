package zyake.apps.jenkinsjobexecutor.config;

import zyake.apps.jenkinsjobexecutor.config.rules.ParserRule;
import zyake.apps.jenkinsjobexecutor.config.rules.ValidationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultArgumentParser implements ArgumentParser {

    public Map<String, Argument> parse(String[] args, ParserRule rule) {
        Map<String, Argument> arguments = doParse(args);
        doValidate(rule, arguments);

        return arguments;
    }

    protected void doValidate(ParserRule rule, Map<String, Argument> arguments) {
        ValidationContext context = new ValidationContext();
        rule.checkRule(context, arguments);

        if ( context.validationFailed() ) {
            throw new ConfigException("validation failed: " + context.getResults());
        }
    }

    protected Map<String, Argument> doParse(String[] args) {
        Map<String, Argument> parsedArgs = new HashMap<>();
        String key = null;
        List<String> values = new ArrayList<>();
        for ( String arg : args ) {
            boolean isKey = arg.startsWith("-");
            if ( isKey ) {
                boolean existsPredKey = key != null;
                if ( existsPredKey ) {
                    Argument argument = new Argument(key, values);
                    parsedArgs.put(key, argument);
                    values = new ArrayList<>();
                }

                key = arg.substring(1);
                continue;
            }

            boolean noKeyValue = key == null;
            if ( noKeyValue ) {
                throw new ConfigException("argument syntax error: argument=" + args);
            }
            values.add(arg);
        }

        boolean existsLastArg = ! parsedArgs.containsKey(key);
        if ( existsLastArg ) {
            parsedArgs.put(key, new Argument(key,  values));
        }

        return parsedArgs;
    }
}
