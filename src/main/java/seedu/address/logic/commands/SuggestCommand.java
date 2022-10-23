package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_TIME_OF_WEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonSuggestionPredicate;

/**
 * Suggests a list of friends to play Minecraft with based on a set of constraints.
 * Keyword matching is done through "string contains" and is not case-sensitive.
 */
public class SuggestCommand extends Command {

    public static final String COMMAND_WORD = "suggest";
    public static final String DESCRIPTION = "Suggests a list of friends based on a given set of constraints.\n\n"
            + "Provide some days of week (mon, tue...sun) and some times (0600, 2130, etc.) "
            + "and they will be matched against the availability periods of the player.\n\n"
            + "Provide keywords, and they will be matched against all attributes "
            + "(Minecraft username, social handles etc.) by checking if the attributes "
            + "contain the given keywords. All keywords must be matched.";
    public static final String PARAMETER = "[" + PREFIX_KEYWORD + "KEYWORD] "
            + "[" + PREFIX_DAY_TIME_OF_WEEK + "DAY TIME OF WEEK] "
            + "[" + PREFIX_KEYWORD + "KEYWORD]\n"
            + "All day time of week must come before all keywords";
    public static final String EXAMPLE = COMMAND_WORD + " "
            + PREFIX_DAY_TIME_OF_WEEK + "mon@1755 "
            + PREFIX_DAY_TIME_OF_WEEK + "fri@2355 "
            + PREFIX_KEYWORD + "sally tan " + PREFIX_KEYWORD + "sally.245\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_KEYWORD + "91234567 "
            + PREFIX_KEYWORD + "sally.tan.454r33 "
            + PREFIX_KEYWORD + "sally.tan.454@gmail.com";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": " + DESCRIPTION + "\n\n"
            + "Parameters:\n"
            + PARAMETER + "\n\n"
            + "Example: " + EXAMPLE;

    private final PersonSuggestionPredicate predicate;

    public SuggestCommand() {
        this.predicate = null;
    }

    public SuggestCommand(PersonSuggestionPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getParameter() {
        return PARAMETER;
    }

    @Override
    public String getExample() {
        return EXAMPLE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SuggestCommand // instanceof handles nulls
                && predicate.equals(((SuggestCommand) other).predicate)); // state check
    }
}
