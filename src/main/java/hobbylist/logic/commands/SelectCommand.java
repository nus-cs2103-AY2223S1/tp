package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import hobbylist.commons.core.Messages;
import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.model.Model;
import hobbylist.model.activity.Activity;

/**
 * Select an activity in the current list by its index to display all details about it
 */
public class SelectCommand extends Command {
    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Select an activity in the current list "
            + "by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private static final String MESSAGE_SELECT_ACTIVITY_SUCCESS = "Selected activity: %1$s";

    private final Index targetIndex;

    public SelectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Activity> lastShownList = model.getFilteredActivityList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        Activity activityToSelect = lastShownList.get(targetIndex.getZeroBased());
        model.selectActivity(activityToSelect);
        return new CommandResult(
                String.format(MESSAGE_SELECT_ACTIVITY_SUCCESS, activityToSelect));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectCommand // instanceof handles nulls
                && targetIndex.equals(((SelectCommand) other).targetIndex)); // state check
    }
}
