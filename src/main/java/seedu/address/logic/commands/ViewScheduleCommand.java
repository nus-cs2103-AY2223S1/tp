package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import seedu.address.model.Model;

/**
 * Views all slots in the schedule which satisfies selection requirements
 */
public class ViewScheduleCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views all timeslots which satisfies all selection "
            + "requirements.\n"
            + "Format: view schedule [w/ WEEKDAY] [m/ MODULE]\n"
            + "Example: " + COMMAND_WORD + " m/ CS2103T";

    public static final String MESSAGE_SUCCESS = "Showed all schedules";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
        return new CommandResult(MESSAGE_SUCCESS, false, false,
                false, false, false, true);
    }

}
