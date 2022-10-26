package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.commons.core.Messages;
import hobbylist.model.Model;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;

/**
 * Finds and lists all activities in HobbyList whose name or description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    private static String commandWord = "find";

    public static final String MESSAGE_USAGE = commandWord + ": Finds all activities whose names or descriptions"
            + "contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + commandWord + " sleep exercise code";

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
