package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;

import java.util.List;
import java.util.Optional;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.medication.exceptions.DuplicateMedicationException;
import seedu.uninurse.model.person.Patient;

/**
 * Edits the details of an existing medication for a patient.
 */
public class EditMedicationCommand extends EditGenericCommand {
    // tentative syntax; TODO: integrate with EditGenericCommand
    public static final String COMMAND_WORD = "editMedication";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the medication identified by the index number in the medication list of the patient "
            + "identified by the index number used in the last patient listing.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: PATIENT_INDEX (must be a positive integer) "
            + "MEDICATION_INDEX (must be a positive integer) "
            + PREFIX_MEDICATION + "MEDICATION_TYPE | MEDICATION_DOSAGE\n"
            + "Example: " + COMMAND_WORD + " 2 " + " 1 "
            + PREFIX_MEDICATION + "Ampicillin | 0.5 IV every 6 hours";

    public static final String MESSAGE_EDIT_MEDICATION_SUCCESS = "Edited medication %1$d of %2$s:\n"
            + "Before: %3$s\n"
            + "After: %4$s";
    public static final String MESSAGE_NOT_EDITED = "Medication to edit must be provided.";
    public static final String MESSAGE_EDIT_DUPLICATE_MEDICATION =
            "This medication already exists in %1$s's medication list.";

    public static final CommandType EDIT_MEDICATION_COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index medicationIndex;
    private final EditMedicationDescriptor editMedicationDescriptor;

    /**
     * Creates an EditMedicationCommand to edit a {@code Medication} from the specified patient.
     *
     * @param patientIndex of the patient in the filtered patient list to edit.
     * @param medicationIndex of the medication to be edited.
     * @param editMedicationDescriptor descriptor with details to edit for the medication.
     */
    public EditMedicationCommand(Index patientIndex, Index medicationIndex,
            EditMedicationDescriptor editMedicationDescriptor) {
        requireAllNonNull(patientIndex, medicationIndex, editMedicationDescriptor);

        this.patientIndex = patientIndex;
        this.medicationIndex = medicationIndex;
        this.editMedicationDescriptor = editMedicationDescriptor;
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

        Medication initialMedication = initialMedicationList.get(medicationIndex.getZeroBased());
        Medication updatedMedication = new Medication(
                editMedicationDescriptor.getType().orElse(initialMedication.getType()),
                editMedicationDescriptor.getDosage().orElse(initialMedication.getDosage()));

        MedicationList updatedMedicationList;

        try {
            updatedMedicationList = initialMedicationList.edit(medicationIndex.getZeroBased(), updatedMedication);
        } catch (DuplicateMedicationException exception) {
            throw new CommandException(String.format(MESSAGE_EDIT_DUPLICATE_MEDICATION, patientToEdit.getName()));
        }

        Patient editedPatient = new Patient(patientToEdit, updatedMedicationList);

        PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_EDIT_MEDICATION_SUCCESS,
                medicationIndex.getOneBased(), editedPatient.getName(), initialMedication, updatedMedication),
                EDIT_MEDICATION_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMedicationCommand)) {
            return false;
        }

        // state check
        EditMedicationCommand command = (EditMedicationCommand) other;
        return patientIndex.equals(command.patientIndex)
                && medicationIndex.equals(command.medicationIndex)
                && editMedicationDescriptor.equals(command.editMedicationDescriptor);
    }

    /**
     * Stores the details to edit the medication with. Each non-empty field value
     * will replace the corresponding field value of the medication.
     */
    public static class EditMedicationDescriptor {
        private final Optional<String> medicationType;
        private final Optional<String> medicationDosage;

        /**
         * Constructs a {@code EditMedicationDescriptor} that contains optional fields to edit an existing medication.
         *
         * @param medicationType An optional valid medication type.
         * @param medicationDosage An optional valid dosage amount.
         */
        public EditMedicationDescriptor(Optional<String> medicationType, Optional<String> medicationDosage) {
            this.medicationType = medicationType;
            this.medicationDosage = medicationDosage;
        }

        public Optional<String> getType() {
            return medicationType;
        }

        public Optional<String> getDosage() {
            return medicationDosage;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMedicationDescriptor)) {
                return false;
            }

            // state check
            EditMedicationDescriptor e = (EditMedicationDescriptor) other;

            return getType().equals(e.getType())
                    && getDosage().equals(e.getDosage());
        }
    }
}
