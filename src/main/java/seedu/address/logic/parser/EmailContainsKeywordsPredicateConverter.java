package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import picocli.CommandLine;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;

/**
 * Converter from {@code String} to {@code EmailContainsKeywordsPredicate}.
 */
public class EmailContainsKeywordsPredicateConverter implements CommandLine.IParameterConsumer {

    /**
     * Matches any alphanumeric characters excluding underscores, special characters from {@code Email}, and
     * @ symbol.
     */
    public static final String VALIDATION_REGEX =
            "(" + Email.ALPHANUMERIC_NO_UNDERSCORE + "|" + "[" + Email.SPECIAL_CHARACTERS + "@]" + ")*";
    public static final String MESSAGE_CONSTRAINTS = "Keywords should only contain alphanumeric characters and these "
            + "special characters, excluding the parentheses, (" + Email.SPECIAL_CHARACTERS + ") without any spaces";

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

        argSpec.setValue(new EmailContainsKeywordsPredicate(keywords));
    }
}
