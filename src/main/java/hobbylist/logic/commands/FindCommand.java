package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.commons.core.Messages;
import hobbylist.model.Model;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;

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

    private final NameOrDescContainsKeywordsPredicate predicate;

    public FindCommand(NameOrDescContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
