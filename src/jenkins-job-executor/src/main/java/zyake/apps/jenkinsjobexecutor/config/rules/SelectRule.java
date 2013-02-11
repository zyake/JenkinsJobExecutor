package zyake.apps.jenkinsjobexecutor.config.rules;

import zyake.apps.jenkinsjobexecutor.config.Argument;

import java.util.*;

public class SelectRule implements ParserRule {

    private Set<String> selections = new HashSet<>();

    public SelectRule(String... selections) {
        for ( String selection : selections ) {
            this.selections.add(selection);
        }
    }

    @Override
    public void checkRule(ValidationContext context, Map<String, Argument> args) {
        Set<String> foundSelections = new HashSet<>();
        for ( String selection : selections ) {
            boolean selectionFound = args.containsKey(selection);
            if ( selectionFound ) {
                foundSelections.add(selection);
            }
        }

        boolean selectionNotFound = foundSelections.size() == 0;
        if ( selectionNotFound ) {
            context.addResult("selection arg",
                    "selection not found; selections=" + new TreeSet<>(selections));
            return;
        }

        boolean multipleSelectionSpecified = foundSelections.size() > 1;
        if ( multipleSelectionSpecified ) {
            context.addResult("selection arg",
                    "multiple selection specified: selections=" + new TreeSet<>(selections) + ", specified selections=" + new TreeSet<>(foundSelections));
        }
    }

    @Override
    public String toString() {
        return "type=selection, selections=" + new TreeSet<>(selections);
    }
}
