package hobbylist.logic.commands;

import static hobbylist.commons.util.CollectionUtil.requireAllNonNull;
import static hobbylist.model.Model.PREDICATE_SHOW_ALL_ACTIVITIES;

import java.util.List;

import hobbylist.commons.core.Messages;
import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.model.Model;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Status;

/**
 * Changes the completion status of an existing activity in HobbyList.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the completion status of the activity identified "
            + "by the index number used in the last activity listing. "
            + "Existing status will be overwritten by the input. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "s/ [STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "s/ ONGOING";

    public static final String MESSAGE_ADD_STATUS_SUCCESS = "Added status to Activity: %1$s";

    private final Index index;
    private final Status status;

    /**
     * @param index of the activity in the filtered activity list to change status
     * @param status Status to be tagged to the activity
     */
    public StatusCommand(Index index, Status status) {
        requireAllNonNull(index, status);

        this.index = index;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Activity> lastShownList = model.getFilteredActivityList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        Activity activityToEdit = lastShownList.get(index.getZeroBased());

        Activity editedActivity = new Activity(
                activityToEdit.getName(), activityToEdit.getDescription(), activityToEdit.getTags(),
                activityToEdit.getDate(), status);

        model.setActivity(activityToEdit, editedActivity);
        model.updateFilteredActivityList(PREDICATE_SHOW_ALL_ACTIVITIES);

        return new CommandResult(String.format(MESSAGE_ADD_STATUS_SUCCESS, editedActivity));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof StatusCommand)) {
            return false;
        }

        // state check
        StatusCommand s = (StatusCommand) other;
        return index.equals(s.index)
                && status.equals(s.status);
    }
}
