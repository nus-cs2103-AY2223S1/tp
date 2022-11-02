package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.person.Patient;

/**
 * Clears the uninurse book.
 */
public class ClearCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Listed patients have been cleared!";

    public static final CommandType CLEAR_COMMAND_TYPE = CommandType.CLEAR;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Patient> lastShownList = new ArrayList<Patient>(model.getFilteredPersonList());
        PatientListTracker patientListTracker = model.clearPersons(lastShownList);
        return new CommandResult(MESSAGE_SUCCESS, CLEAR_COMMAND_TYPE, patientListTracker);
    }
}
