package zyake.apps.jenkinsjobexecutor.config;

import zyake.apps.jenkinsjobexecutor.config.rules.ParserRule;

import java.util.Map;

/**
 * 引数コマンドをパースするためのパーサの実装。
 * パース対象のフォーマットは以下のとおり。
 * <pre>
 * EXP::=PARAMETER*
 * PARAMETER::='-'KEY VALUE*
 * KEY=\w+
 * VALUE=\w+
 * </pre>
 */
public interface ArgumentParser {

    Map<String, Argument> parse(String[] args, ParserRule rule);
}
