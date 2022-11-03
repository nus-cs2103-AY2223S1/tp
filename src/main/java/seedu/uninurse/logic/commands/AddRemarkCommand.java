package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.remark.exceptions.DuplicateRemarkException;

/**
 * Add a remark to an existing patient in the patient list.
 */
public class AddRemarkCommand extends AddGenericCommand {
    // tentative syntax; TODO: integrate with AddGenericCommand
    public static final String COMMAND_WORD = "addRemark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a remark to the patient identified "
            + "by the index number used in the last patient listing.\n"
            + "Parameters: PATIENT_INDEX (must be a positive integer) "
            + PREFIX_REMARK + "REMARK\n"
            + "Example: " + COMMAND_WORD
            + " 2 " + PREFIX_REMARK + "Allergic to Amoxicillin";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "New remark added to %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_REMARK = "This remark already exists in %1$s's remark list";
    public static final CommandType ADD_REMARK_COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index index;
    private final Remark remark;

    /**
     * Creates an AddRemarkCommand to add a {@code Remark} to the specified person.
     * @param index The index of the person in the filtered person list to add the remark.
     * @param remark The remark of the person to be added to.
     */
    public AddRemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        RemarkList updatedRemarkList;

        try {
            updatedRemarkList = patientToEdit.getRemarks().add(remark);
        } catch (DuplicateRemarkException dre) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_REMARK, patientToEdit.getName()));
        }

        Patient editedPatient = new Patient(patientToEdit, updatedRemarkList);

        PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_ADD_REMARK_SUCCESS, editedPatient.getName(), remark),
                ADD_REMARK_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddRemarkCommand)) {
            return false;
        }

        // state check
        AddRemarkCommand command = (AddRemarkCommand) other;
        return index.equals(command.index) && remark.equals((command.remark));
    }
}
