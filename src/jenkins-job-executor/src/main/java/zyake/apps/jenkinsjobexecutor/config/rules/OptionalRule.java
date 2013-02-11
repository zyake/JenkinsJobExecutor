package zyake.apps.jenkinsjobexecutor.config.rules;

import zyake.apps.jenkinsjobexecutor.config.Argument;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueCounter;
import zyake.apps.jenkinsjobexecutor.config.rules.validation.ValueValidator;

import java.util.Arrays;
import java.util.Map;

/**
 * 設定しなくても良いオプション項目のルール実装。
 * オプション項目ではデフォルト値をもたせることができるため、
 * チェック時に項目が存在しなかった場合、デフォルト値を設定する。
 */
public class OptionalRule extends AbstractRule {

    private String key;

    private Argument defaultArg;

    public OptionalRule(String key, ValueValidator validator, ValueCounter counter, String... defaultValues) {
        super(validator, counter);
        this.key = key;
        this.defaultArg = new Argument(key, Arrays.asList(defaultValues));
    }

    public OptionalRule(String key, ValueValidator validator, ValueCounter counter) {
        super(validator, counter);
        this.key = key;
    }

    /**
     * オプション項目のチェックを実行する。
     * オプション項目が存在しなかった場合は、デフォルト値を設定する。
     * @param context
     * @param args
     */
    @Override
    public void checkRule(ValidationContext context, Map<String, Argument> args) {
        boolean optionalValueNotFound = ! args.containsKey(key);
        if ( optionalValueNotFound ) {
            boolean hasDefaultArgs = defaultArg != null;
            if ( hasDefaultArgs) {
                args.put(key, defaultArg);
            }

            return;
        }
        doValidate(key, context, args);
    }

    @Override
    public String toString() {
        return "type=optional, key=" + key + ", validator=" + validator + ", counter=" + counter + ", default arg=" + defaultArg;
    }
}
