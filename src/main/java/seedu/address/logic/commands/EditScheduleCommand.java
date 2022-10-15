package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_OF_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEKDAY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.schedule.Schedule;

/**
 * Edit an existing schedule
 */
public class EditScheduleCommand extends Command {

    public static final String COMMAND_WORD = "sedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an existing schedule. "
            + "Parameters: "
            + PREFIX_MODULE_OF_SCHEDULE + "MODULE CODE "
            + PREFIX_CLASS_CATEGORY + "CLASS CATEGORY "
            + PREFIX_WEEKDAY + "NEW WEEKDAY "
            + PREFIX_CLASS_TIME + "NEW TIME PERIOD "
            + PREFIX_CLASS_VENUE + "NEW CLASS VENUE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_OF_SCHEDULE + "CS2103T "
            + PREFIX_CLASS_CATEGORY + "lec "
            + PREFIX_WEEKDAY + "Thursday"
            + PREFIX_CLASS_TIME + "12:00-14:00 "
            + PREFIX_CLASS_VENUE + "LT17 ";

    public static final String MESSAGE_SUCCESS = "Schedule edited: %1$s";
    public static final String MESSAGE_SCHEDULE_NOT_EXIST = "The schedule doesn't exist";
    public static final String MESSAGE_CONFLICT_SCHEDULE = "This schedule conflicts with other schedules";
    public static final String MESSAGE_DISALLOWED_MODIFICATION = "Only the venue, slot, and weekday are allowed to be "
            + "modified";
    private final Schedule scheduleAfterEdit;

    /**
     * Creates an EditScheduleCommand to edit the specified schedule.
     */
    public EditScheduleCommand(Schedule toEdit) {
        requireNonNull(toEdit);
        this.scheduleAfterEdit = toEdit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Schedule target = model.findSchedule(scheduleAfterEdit);
        if (target == null) {
            throw new CommandException(MESSAGE_SCHEDULE_NOT_EXIST);
        }
        if (model.conflictSchedule(scheduleAfterEdit)) {
            throw new CommandException(MESSAGE_CONFLICT_SCHEDULE);
        }
        model.editSchedule(target, scheduleAfterEdit);
        return new CommandResult(String.format(MESSAGE_SUCCESS, scheduleAfterEdit), false, false, false,
                false, false, true);

    }
}
