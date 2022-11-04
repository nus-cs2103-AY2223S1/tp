package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_MEDICATION_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

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
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_MEDICATION_INDEX
            + ": Edits a medication of a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_MEDICATION_INDEX + " MEDICATION_INDEX "
            + PREFIX_MEDICATION + "<MEDICATION_TYPE> | <DOSAGE>\n"
            + "Examples:\n" + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 " + PREFIX_OPTION_MEDICATION_INDEX
            + " 1 " + PREFIX_MEDICATION + "Amoxicillin\n"
            + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 " + PREFIX_OPTION_MEDICATION_INDEX
            + " 1 " + PREFIX_MEDICATION + "| 0.5 g every 8 hours\n"
            + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 " + PREFIX_OPTION_MEDICATION_INDEX
            + " 1 " + PREFIX_MEDICATION + "Amoxicillin | 0.5 g every 8 hours";
    public static final String MESSAGE_SUCCESS = "Edited medication %1$d of %2$s:\n"
            + "Before: %3$s\n"
            + "After: %4$s";
    public static final String MESSAGE_FAILURE = "At least one field to edit must be provided.";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index medicationIndex;
    private final EditMedicationDescriptor editMedicationDescriptor;

    /**
     * Creates an EditMedicationCommand to edit a Medication from the specified patient.
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
        requireAllNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        MedicationList initialMedicationList = patientToEdit.getMedications();

        if (medicationIndex.getZeroBased() >= initialMedicationList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEDICATION_INDEX);
        }

        try {
            Medication initialMedication = initialMedicationList.get(medicationIndex.getZeroBased());
            Medication updatedMedication = new Medication(
                    editMedicationDescriptor.getType().orElse(initialMedication.getType()),
                    editMedicationDescriptor.getDosage().orElse(initialMedication.getDosage()));
            MedicationList updatedMedicationList = initialMedicationList.edit(medicationIndex.getZeroBased(),
                    updatedMedication);

            Patient editedPatient = new Patient(patientToEdit, updatedMedicationList);

            PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    medicationIndex.getOneBased(), editedPatient.getName(), initialMedication, updatedMedication),
                    COMMAND_TYPE, patientListTracker);
        } catch (DuplicateMedicationException dme) {
            throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_MEDICATION, patientToEdit.getName()));
        }
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
        EditMedicationCommand o = (EditMedicationCommand) other;
        return patientIndex.equals(o.patientIndex)
                && medicationIndex.equals(o.medicationIndex)
                && editMedicationDescriptor.equals(o.editMedicationDescriptor);
    }

    /**
     * Stores the details to edit the medication with. Each non-empty field value
     * will replace the corresponding field value of the medication.
     */
    public static class EditMedicationDescriptor {
        private final Optional<String> medicationType;
        private final Optional<String> medicationDosage;

        /**
         * Constructs a EditMedicationDescriptor that contains optional fields to edit an existing medication.
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

        public boolean isEmpty() {
            return medicationType.isEmpty() && medicationDosage.isEmpty();
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
            EditMedicationDescriptor o = (EditMedicationDescriptor) other;

            return medicationType.equals(o.medicationType)
                    && medicationDosage.equals(o.medicationDosage);
        }
    }
}
