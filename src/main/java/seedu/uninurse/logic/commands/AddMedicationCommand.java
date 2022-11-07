package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.commands.exceptions.DuplicateEntryException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.medication.exceptions.DuplicateMedicationException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * Add a medication to an existing patient in the patient list.
 */
public class AddMedicationCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX
            + ": Adds a medication to a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_MEDICATION + "MEDICATION_TYPE | DOSAGE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_MEDICATION + "Amoxicillin | 0.5 g every 8 hours";
    public static final String MESSAGE_SUCCESS = "New medication added to %1$s: %2$s";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index index;
    private final Medication medication;

    /**
     * Creates an AddMedicationCommand to add a medication to the specified patient.
     *
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
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit;

        try {
            patientToEdit = model.getPatient(lastShownList.get(index.getZeroBased()));
        } catch (PatientNotFoundException pnfe) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT);
        }

        try {
            MedicationList updatedMedicationList = patientToEdit.getMedications().add(medication);

            Patient editedPatient = new Patient(patientToEdit, updatedMedicationList);

            PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_SUCCESS, editedPatient.getName(), medication),
                    COMMAND_TYPE, personListTracker);
        } catch (DuplicateMedicationException dme) {
            model.setPatientOfInterest(patientToEdit);
            throw new DuplicateEntryException(
                    String.format(Messages.MESSAGE_DUPLICATE_MEDICATION, patientToEdit.getName()));
        }
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
        AddMedicationCommand o = (AddMedicationCommand) other;
        return index.equals(o.index) && medication.equals((o.medication));
    }
}
