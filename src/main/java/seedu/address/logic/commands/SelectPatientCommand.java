package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Select the given patient and filter appointment list by given patient.
 */
public class SelectPatientCommand extends Command {
    public static final CommandWord COMMAND_WORD =
            new CommandWord("selectpatient", "slp");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects the indicated patient and shows the patient's"
            + "appointments and bills.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Selected Patient: %1$s";

    private final Index index;

    /**
     * @param index of the patient in the filtered patient list to select
     */
    public SelectPatientCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToSelect = lastShownList.get(index.getZeroBased());

        model.selectPatient(patientToSelect);
        return new CommandResult(String.format(MESSAGE_SUCCESS, patientToSelect));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SelectPatientCommand)) {
            return false;
        }

        SelectPatientCommand e = (SelectPatientCommand) other;
        return index.equals(e.index);
    }
}
