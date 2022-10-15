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
 * Adds a schedule to a module
 */
public class AddScheduleCommand extends Command {

    public static final String COMMAND_WORD = "sadd";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_MODULE_OF_SCHEDULE + "MODULE CODE "
            + PREFIX_WEEKDAY + "WEEKDAY "
            + PREFIX_CLASS_TIME + "TIME PERIOD "
            + PREFIX_CLASS_CATEGORY + "CLASS CATEGORY "
            + PREFIX_CLASS_VENUE + "CLASS VENUE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_OF_SCHEDULE + "CS2103T "
            + PREFIX_WEEKDAY + "Friday "
            + PREFIX_CLASS_TIME + "16:00-18:00 "
            + PREFIX_CLASS_CATEGORY + "lec "
            + PREFIX_CLASS_VENUE + "I3-AUD ";

    public static final String MESSAGE_SUCCESS = "New schedule added: %1$s";
    public static final String MESSAGE_MODULE_NOT_EXIST = "The module doesn't exist";
    public static final String MESSAGE_CONFLICT_SCHEDULE = "This schedule conflicts with other schedules";
    private final Schedule scheduleToAdd;

    /**
     * Creates an AddScheduleCommand to add the specified schedule.
     */
    public AddScheduleCommand(Schedule toAdd) {
        requireNonNull(toAdd);
        this.scheduleToAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getModuleByModuleCode(scheduleToAdd.getModule()) == null) {
            throw new CommandException(MESSAGE_MODULE_NOT_EXIST);
        }
        if (model.conflictSchedule(scheduleToAdd)) {
            throw new CommandException(MESSAGE_CONFLICT_SCHEDULE);
        }
        model.addSchedule(scheduleToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, scheduleToAdd),
                false, false, false,
                false, false, true);
    }
}
