package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.person.Patient;

/**
 * Deletes a patient identified using it's displayed index from the Patient list.
 */
public class DeletePatientCommand extends DeleteGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Patient: %1$s";

    public static final CommandType DELETE_PATIENT_COMMAND_TYPE = CommandType.DELETE_PATIENT;

    private final Index targetIndex;

    public DeletePatientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToDelete = lastShownList.get(targetIndex.getZeroBased());
        PatientListTracker patientListTracker = model.deletePerson(patientToDelete);
        model.setPatientOfInterest(patientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, patientToDelete),
                DELETE_PATIENT_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePatientCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePatientCommand) other).targetIndex)); // state check
    }
}
