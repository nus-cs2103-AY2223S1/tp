package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Patient;

/**
 * Deletes a medication from a patient identified using its displayed index from the patient list.
 */
public class DeleteMedicationCommand extends DeleteGenericCommand {
    // tentative syntax; TODO: integrate with DeleteGenericCommand
    public static final String COMMAND_WORD = "deleteMedication";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the medication identified by the index number in the medication list of the patient "
            + "identified by the index number used in the last patient listing.\n"
            + "Parameters: PATIENT_INDEX (must be a positive integer) "
            + "MEDICATION_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_MEDICATION_SUCCESS = "Deleted medication %1$d from %2$s: %3$s";

    public static final CommandType DELETE_MEDICATION_COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index medicationIndex;

    /**
     * Creates an DeleteMedicationCommand to delete a {@code Medication} from the specified patient.
     *
     * @param patientIndex The index of the patient in the filtered person list to delete the medication.
     * @param medicationIndex The index of the medication in the patient's medication list.
     */
    public DeleteMedicationCommand(Index patientIndex, Index medicationIndex) {
        requireAllNonNull(patientIndex, medicationIndex);

        this.patientIndex = patientIndex;
        this.medicationIndex = medicationIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        MedicationList initialMedicationList = patientToEdit.getMedications();

        if (medicationIndex.getZeroBased() >= initialMedicationList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEDICATION_INDEX);
        }

        MedicationList updatedMedicationList = initialMedicationList.delete(medicationIndex.getZeroBased());
        Medication deletedMedication = initialMedicationList.get(medicationIndex.getZeroBased());

        Patient editedPatient = new Patient(patientToEdit, updatedMedicationList);

        PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_DELETE_MEDICATION_SUCCESS, medicationIndex.getOneBased(),
                editedPatient.getName(), deletedMedication), DELETE_MEDICATION_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMedicationCommand)) {
            return false;
        }

        // state check
        DeleteMedicationCommand command = (DeleteMedicationCommand) other;
        return patientIndex.equals(command.patientIndex) && medicationIndex.equals((command.medicationIndex));
    }
}
