package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import hobbylist.commons.core.Messages;
import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.model.Model;
import hobbylist.model.activity.Activity;

/**
 * Deletes an activity identified using it's displayed index from the HobbyList.
 */
public class DeleteCommand extends Command {

    public static final String MESSAGE_DELETE_ACTIVITY_SUCCESS = "Deleted Activity: %1$s";

    private static String commandWord = "delete";

    public static final String MESSAGE_USAGE = commandWord
            + ": Deletes the activity identified by the index number used in the displayed activity list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + commandWord + " 1";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Activity> lastShownList = model.getFilteredActivityList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        Activity activityToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteActivity(activityToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
