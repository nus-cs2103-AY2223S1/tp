package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.watson.model.Model;

/**
 * Lists all persons in the watson book to the user.
 * This starts a while loop that allows the use to input attendance for each student
 */
public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Allows for editing of attendance by subject.\n"
        + "This command has 2 MODES: \n"
        + "1) Edit attendance for ALL students\n"
        + "2) Edit attendance for ONE student\n"
        + "Parameters: MODE (must be 1 or 2). "
        + "1 for modifying ALL students' attendance,\n"
        + "2 for modifying a single student's attendance.";
    public static final String MESSAGE_SUCCESS = "Attendance has been updated!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
