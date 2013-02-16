package zyake.apps.jenkinsjobexecutor.loaders;

import zyake.apps.jenkinsjobexecutor.Job;
import zyake.apps.jenkinsjobexecutor.util.Predicate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ジョブを受け付けるかどうかをフィルタリングする。
 * フィルタリングの優先順位は以下のとおり。
 * <ol>
 *     <li>名前</li>
 *     <li>包含フィルタ</li>
 *     <li>除外フィルタ</li>
 * </ol>
 * どのフィルタリングにも該当しなかったジョブは、
 * 処理対象として受け付けられる。
 */
public class DefaultJobPredicate implements Predicate<Job> {

    private List<Pattern> includePatterns = new ArrayList<>();

    private List<Pattern> excludePatterns = new ArrayList<>();

    private Set<String> targetNames = new HashSet<>();

    public DefaultJobPredicate addIncludePatterns(String... includePatterns) {
        for ( String pattern :  includePatterns ) {
            this.includePatterns.add(Pattern.compile(pattern));
        }

        return this;
    }

    public DefaultJobPredicate addIncludePatterns(List<String> includePatterns) {
        for ( String pattern :  includePatterns ) {
            this.includePatterns.add(Pattern.compile(pattern));
        }

        return this;
    }

    public DefaultJobPredicate addExcludePatterns(String... excludePatterns) {
        for ( String pattern :  excludePatterns ) {
            this.excludePatterns.add(Pattern.compile(pattern));
        }

        return this;
    }

    public DefaultJobPredicate addExcludePatterns(List<String> excludePatterns) {
        for ( String pattern :  excludePatterns ) {
            this.excludePatterns.add(Pattern.compile(pattern));
        }

        return this;
    }

    public DefaultJobPredicate addTargetNames(String... targetNames) {
        for ( String targetName : targetNames ) {
            this.targetNames.add(targetName);
        }

        return this;
    }

    public DefaultJobPredicate addTargetNames(List<String> targetNames) {
        this.targetNames.addAll(targetNames);

        return this;
    }

    @Override
    public boolean evaluate(Job target) {
        String targetName = target.getName();
        boolean isTargetName = targetNames.contains(targetName);
        if ( isTargetName ) {
            return true;
        }

        for ( Pattern include : includePatterns ) {
            Matcher matcher = include.matcher(targetName);
            boolean isIncludeTarget = matcher.find();
            if ( isIncludeTarget ) {
                return true;
            }
        }

        for ( Pattern exclude : excludePatterns ) {
            Matcher matcher = exclude.matcher(targetName);
            boolean isExcludeTarget = matcher.find();
            if ( isExcludeTarget ) {
                return false;
            }
        }

        return true;
    }
}
