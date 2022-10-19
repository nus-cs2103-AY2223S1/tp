package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.schedule.Schedule;

/**
 * Deletes a schedule identified using it's displayed index from the ProfNUS.
 */
public class ClearScheduleCommand extends Command {
    public static final String COMMAND_WORD = "sclear";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clear all current schedules. \n"
            + "Example: " + COMMAND_WORD ;
    public static final String MESSAGE_CLEAR_ALL_SCHEDULES_SUCCESS = "Clear all schedules successfully";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.clearSchedules();

        return new CommandResult(MESSAGE_CLEAR_ALL_SCHEDULES_SUCCESS,
                false, false, false, false,
                false, true);
    }
}
