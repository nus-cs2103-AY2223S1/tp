package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;

import java.util.List;

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
 * Add a medication to an existing patient in the patient list.
 */
public class AddMedicationCommand extends AddGenericCommand {
    // tentative syntax; TODO: integrate with AddGenericCommand
    public static final String COMMAND_WORD = "addMedication";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a medication to the patient identified "
            + "by the index number used in the last patient listing.\n"
            + "Parameters: PATIENT_INDEX (must be a positive integer) "
            + PREFIX_MEDICATION + "MEDICATION_TYPE | MEDICATION_DOSAGE\n"
            + "Example: " + COMMAND_WORD
            + " 2 " + PREFIX_MEDICATION + "Amoxicillin | 0.5 g every 8 hours";

    public static final String MESSAGE_ADD_MEDICATION_SUCCESS = "New medication added to %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_MEDICATION =
            "This medication already exists in %1$s's medication list";

    public static final CommandType ADD_MEDICATION_COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index index;
    private final Medication medication;

    /**
     * Creates an AddMedicationCommand to add a {@code Medication} to the specified patient.
     * @param index The index of the patient in the filtered patient list to add the medication.
     * @param medication The medication of the patient to be added to.
     */
    public AddMedicationCommand(Index index, Medication medication) {
        requireAllNonNull(index, medication);

        this.index = index;
        this.medication = medication;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        MedicationList updatedMedicationList;

        try {
            updatedMedicationList = patientToEdit.getMedications().add(medication);
        } catch (DuplicateMedicationException exception) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_MEDICATION, patientToEdit.getName()));
        }

        Patient editedPatient = new Patient(patientToEdit, updatedMedicationList);

        PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_ADD_MEDICATION_SUCCESS, editedPatient.getName(), medication),
                ADD_MEDICATION_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMedicationCommand)) {
            return false;
        }

        // state check
        AddMedicationCommand command = (AddMedicationCommand) other;
        return index.equals(command.index) && medication.equals((command.medication));
    }
}
