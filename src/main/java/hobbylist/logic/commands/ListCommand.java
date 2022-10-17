package hobbylist.logic.commands;

import static hobbylist.model.Model.PREDICATE_SHOW_ALL_ACTIVITIES;
import static java.util.Objects.requireNonNull;

import hobbylist.model.Model;

/**
 * Lists all activities in the HobbyList to the user.
 */
public class ListCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Listed all activities";

    private static String commandWord = "list";

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
        model.updateFilteredActivityList(PREDICATE_SHOW_ALL_ACTIVITIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
