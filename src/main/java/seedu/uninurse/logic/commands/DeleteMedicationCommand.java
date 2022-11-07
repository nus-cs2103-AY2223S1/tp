package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_MEDICATION_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * Deletes a medication from a patient identified using its displayed index from the patient list.
 */
public class DeleteMedicationCommand extends DeleteGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_MEDICATION_INDEX
            + ": Deletes a medication from a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_MEDICATION_INDEX + " MEDICATION_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_MEDICATION_INDEX + " 1";
    public static final String MESSAGE_SUCCESS = "Deleted medication %1$d from %2$s: %3$s";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index medicationIndex;

    /**
     * Creates an DeleteMedicationCommand to delete a medication from the specified patient.
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
        requireAllNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit;

        try {
            patientToEdit = model.getPatient(lastShownList.get(patientIndex.getZeroBased()));
        } catch (PatientNotFoundException pnfe) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT);
        }

        MedicationList initialMedicationList = patientToEdit.getMedications();

        if (medicationIndex.getZeroBased() >= initialMedicationList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEDICATION_INDEX);
        }

        MedicationList updatedMedicationList = initialMedicationList.delete(medicationIndex.getZeroBased());
        Medication deletedMedication = initialMedicationList.get(medicationIndex.getZeroBased());

        Patient editedPatient = new Patient(patientToEdit, updatedMedicationList);

        PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_SUCCESS, medicationIndex.getOneBased(),
                editedPatient.getName(), deletedMedication), COMMAND_TYPE, personListTracker);
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
        DeleteMedicationCommand o = (DeleteMedicationCommand) other;
        return patientIndex.equals(o.patientIndex) && medicationIndex.equals((o.medicationIndex));
    }
}
