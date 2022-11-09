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
            + "Provide some DayTimeInWeek (eg. mon@1930) "
            + "and they will be matched against the availability periods of the player. "
            + "You can type \"now\" to find friends that are online currently.\n\n"
            + "Provide keywords, and they will be matched against all attributes "
            + "(Minecraft username, social handles etc.) by checking if the attributes "
            + "contain the given keywords. All keywords must be matched.";
    public static final String PARAMETERS =
            "[" + PREFIX_DAY_TIME_OF_WEEK + "DAY TIME OF WEEK] "
            + "[" + PREFIX_KEYWORD + "KEYWORD]\n"
            + "All day time of week must come before all keywords";
    public static final String EXAMPLES = COMMAND_WORD + " "
            + PREFIX_DAY_TIME_OF_WEEK + "mon@1755 "
            + PREFIX_DAY_TIME_OF_WEEK + "fri@2355 "
            + PREFIX_DAY_TIME_OF_WEEK + "now "
            + PREFIX_KEYWORD + "sally tan " + PREFIX_KEYWORD + "sally.245\n"
            + COMMAND_WORD + " "
            + PREFIX_KEYWORD + "91234567 "
            + PREFIX_KEYWORD + "sally.tan.454r33 "
            + PREFIX_KEYWORD + "sally.tan.454@gmail.com";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": " + DESCRIPTION + "\n\n"
            + "Parameters:\n"
            + PARAMETERS + "\n\n"
            + "Example: " + EXAMPLES;

    private final PersonSuggestionPredicate predicate;

    /**
     * Default constructor for {@code SuggestCommand}.
     */
    public SuggestCommand() {
        this.predicate = null;
    }

    /**
     * Constructs a {@code SuggestCommand}.
     *
     * @param predicate The PersonSuggestionPredicate for the SuggestCommand
     *                  to run on.
     */
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
    public String getParameters() {
        return PARAMETERS;
    }

    @Override
    public String getExamples() {
        return EXAMPLES;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SuggestCommand // instanceof handles nulls
                && predicate.equals(((SuggestCommand) other).predicate)); // state check
    }
}
