package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import hobbylist.commons.core.Messages;
import hobbylist.model.Model;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.DateMatchesGivenDatePredicate;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;
import hobbylist.model.activity.RatingMatchesGivenValuePredicate;

/**
 * Finds and lists all activities in HobbyList whose name or description contains any of the argument keywords.
 * Find activities of certain rate.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all activities whose names or descriptions"
            + "contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers "
            + "or find all activities on a certain date/in a certain month/year.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...or yyyy-mm-dd/yyyy-mm/yyyy\n"
            + "Example: " + COMMAND_WORD + " sleep exercise code\n"
            + "Example: " + COMMAND_WORD + " 1974-02-02\n";

    private static String commandWord = "find";

    private final Predicate<Activity> predicate;

    public FindCommand(NameOrDescContainsKeywordsPredicate namePredicate,
                       DateMatchesGivenDatePredicate datePredicate,
                       RatingMatchesGivenValuePredicate ratingPredicate) {
        this.predicate = namePredicate.or(datePredicate)
                                      .or(ratingPredicate);
    }

    /**
     * Sets the command word for the command.
     * @param word Word to set command to.
     */
    public static void setCommandWord(String word) {
        commandWord = word;
    }

    /**
     * Gets the command word for the command.
     * @return Command word.
     */
    public static String getCommandWord() {
        return commandWord;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredActivityList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW, model.getFilteredActivityList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
