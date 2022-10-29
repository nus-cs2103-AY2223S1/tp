package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.commons.core.Messages;
import hobbylist.model.Model;
import hobbylist.model.activity.StatusMatchesGivenStatus;

/**
 * Finds and lists all activities in HobbyList whose status matches the given status.
 * Status matching is case insensitive.
 */
public class FindStatusCommand extends Command {
    private static String commandWord = "findStatus";

    public static final String MESSAGE_USAGE = commandWord + ": Finds all activities whose status matches "
            + "the given status (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: STATUS...\n"
            + "Example: " + commandWord + " ONGOING";

    private final StatusMatchesGivenStatus predicate;

    public FindStatusCommand(StatusMatchesGivenStatus predicate) {
        this.predicate = predicate;
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
                || (other instanceof FindStatusCommand // instanceof handles nulls
                && predicate.equals(((FindStatusCommand) other).predicate)); // state check
    }
}
