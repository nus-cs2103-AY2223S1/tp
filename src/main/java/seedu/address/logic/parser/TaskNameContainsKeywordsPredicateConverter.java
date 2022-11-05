package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import picocli.CommandLine;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;

/**
 * Converter from {@code String} to {@code TaskNameContainsKeywordsPredicate}.
 */
public class TaskNameContainsKeywordsPredicateConverter implements CommandLine.IParameterConsumer {

    public static final String VALIDATION_REGEX = "[^\\s'\"]+";
    public static final String MESSAGE_CONSTRAINTS = "Keywords should not contain any space or quotation marks (' and"
            + "\")";

    @Override
    public void consumeParameters(Stack<String> args, CommandLine.Model.ArgSpec argSpec,
                                  CommandLine.Model.CommandSpec commandSpec) {
        List<String> keywords = new ArrayList<>();
        while (!args.isEmpty()) {
            String keyword = args.peek();
            if (!keyword.matches(VALIDATION_REGEX)) {
                throw new CommandLine.ParameterException(commandSpec.commandLine(), MESSAGE_CONSTRAINTS);
            }
            args.pop();
            keywords.add(keyword);
        }

        argSpec.setValue(new TaskNameContainsKeywordsPredicate(keywords));
    }
}
