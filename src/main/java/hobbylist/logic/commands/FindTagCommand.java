package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.commons.core.Messages;
import hobbylist.model.Model;
import hobbylist.model.activity.TagMatchesKeywordPredicate;

/**
 * Finds and lists all activities in HobbyList whose tag matches any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTagCommand extends Command {

    private static String commandWord = "findTag";

    public static final String MESSAGE_USAGE = commandWord + ": Finds all activities whose tags match "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + commandWord + " sport";

    private final TagMatchesKeywordPredicate predicate;

    public FindTagCommand(TagMatchesKeywordPredicate predicate) {
        this.predicate = predicate;
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
                || (other instanceof FindTagCommand // instanceof handles nulls
                && predicate.equals(((FindTagCommand) other).predicate)); // state check
    }
}
