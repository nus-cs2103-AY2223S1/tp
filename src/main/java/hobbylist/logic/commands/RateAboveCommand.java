package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.Model;
import hobbylist.model.activity.RatePredicate;


/**
 * Lists all activities in HobbyList whose rate is above certain value.
 * The required bound should within 0-5(inclusive).
 */
public class RateAboveCommand extends Command {

    public static final String COMMAND_WORD = "r/above";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": List all activities whose rate is above certain value\n"
            + "Parameters: int rateBound...\n"
            + "Example: " + COMMAND_WORD + " 3";
    public static final String RESPOND_MESSAGE = "List all activities whose rate is above ";
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
                RESPOND_MESSAGE + this.bound + ".");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RateAboveCommand // instanceof handles nulls
                && predicate.equals(((RateAboveCommand) other).predicate)); // state check
    }
}
