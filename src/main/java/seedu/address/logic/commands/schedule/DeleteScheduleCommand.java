package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.schedule.Schedule;

/**
 * Deletes a schedule identified using it's displayed index from the ProfNUS.
 */
public class DeleteScheduleCommand extends Command {

    public static final String COMMAND_WORD = "sdelete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the schedule identified by the index number used in the displayed schedule list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_SCHEDULE_SUCCESS = "Deleted Schedule: %1$s";
    public static final String MESSAGE_SCHEDULE_NOT_EXIST = "The schedule doesn't exist";

    private static final Logger logger = LogsCenter.getLogger(DeleteScheduleCommand.class);
    private final Index target;

    public DeleteScheduleCommand(Index target) {
        this.target = target;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> lastShownSchedules = model.getFilteredScheduleList();
        if (target.getZeroBased() >= lastShownSchedules.size()) {
            logger.warning("Index is invalid.");
            throw new CommandException(MESSAGE_SCHEDULE_NOT_EXIST);
        }
        Schedule scheduleToDelete = lastShownSchedules.get(target.getZeroBased());
        model.deleteSchedule(scheduleToDelete);
        logger.fine("DeleteScheduleCommand executes successfully");
        return new CommandResult(String.format(MESSAGE_DELETE_SCHEDULE_SUCCESS, scheduleToDelete), false, false, false,
                false, false, false, true, false, false);
    }
}
