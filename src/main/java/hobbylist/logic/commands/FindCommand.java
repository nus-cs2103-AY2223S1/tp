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
    public static final String DATE_KEYWORD_PREFIX = "date/";
    public static final String RATING_KEYWORD_PREFIX = "rate/";

    public static final String MESSAGE_INVALID_DATE_VALUE = "Invalid time to search! \n"
            + "Time value should be a year, a month, or a date.";
    public static final String MESSAGE_INVALID_RATING_VALUE = "Invalid rating value to search! \n"
            + "Rating value should be an integer value between 1 and 5 (inclusive).";

    private static String commandWord = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all activities whose names or descriptions "
            + "contain any of the given keywords (case-insensitive)"
            + "or whose dates match the given date/month/year"
            + "or whose ratings match the given rating.\n"
            + "Parameters: [key/KEYWORDS]... [date/TIME] [rate/VALUE] (At least search field must exist).\n"
            + "Example: " + commandWord + " sleep code date/2022-10 rate/4";

    private final Predicate<Activity> namePredicate;
    private final Predicate<Activity> datePredicate;
    private final Predicate<Activity> ratingPredicate;
    private final Predicate<Activity> predicate;

    /**
     * Constructor for FindCommand
     */
    public FindCommand(NameOrDescContainsKeywordsPredicate namePredicate,
                       DateMatchesGivenDatePredicate datePredicate,
                       RatingMatchesGivenValuePredicate ratingPredicate) {
        this.namePredicate = namePredicate;
        this.datePredicate = datePredicate;
        this.ratingPredicate = ratingPredicate;
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
        int listSize = model.getFilteredActivityList().size();
        String displayedMessage;
        if (listSize == 1) {
            displayedMessage = Messages.MESSAGE_ACTIVITY_LISTED_OVERVIEW;
        } else {
            displayedMessage = Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW;
        }
        return new CommandResult(String.format(displayedMessage, listSize));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && (namePredicate.equals(((FindCommand) other).namePredicate))
                && (datePredicate.equals(((FindCommand) other).datePredicate))
                && (ratingPredicate.equals(((FindCommand) other).ratingPredicate))); // state check
    }
}
