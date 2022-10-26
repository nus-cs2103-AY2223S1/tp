package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.Model;
import hobbylist.model.activity.RatePredicate;


/**
 * Finds and lists all activities in HobbyList whose name or description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class RateAboveCommand extends Command {

    public static final String COMMAND_WORD = "r/above";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all activities whose names or descriptions"
            + "contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " sleep exercise code";

    private final RatePredicate predicate;
    private int bound;
    /**
     * Parses the given int to predicate.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RateAboveCommand(int bound) {
        this.bound = bound;
        predicate = new RatePredicate(bound);
    }
    /**
     * Execute command
     */

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredActivityList(predicate);
        return new CommandResult(
                "Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RateAboveCommand // instanceof handles nulls
                && predicate.equals(((RateAboveCommand) other).predicate)); // state check
    }
}
