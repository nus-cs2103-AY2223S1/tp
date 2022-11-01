package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.Model;
import hobbylist.model.activity.RatePredicate;


/**
 * Lists all activities in HobbyList whose rate is above certain value.
 * The required bound should within 0-5 (inclusive).
 */
public class RateAboveCommand extends Command {
    public static final String RESPOND_MESSAGE = "List all activities whose rate is above ";

    private static String commandWord = "r/above";

    public static final String MESSAGE_USAGE = commandWord
            + ": List all activities whose rate is higher or equal to the input value.\n"
            + "Parameters: VALUE (must be an integer)\n"
            + "Example: " + commandWord + " 3";
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
     * Returns the command word.
     * @return the command word.
     */
    public static String getCommandWord() {
        return commandWord;
    }

    /**
     * Sets the command word.
     * @param word the new command word.
     */
    public static void setCommandWord(String word) {
        commandWord = word;
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
