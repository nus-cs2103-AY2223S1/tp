package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Command to show timetable.
 */
public class TimetableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": shows timetable "
            + "identified by the index number used in the displayed person list or user. "
            + "Parameters: user / INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " user ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.commitAddressBook();
        return new CommandResult("", false, false, true);
    }
}
