package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_REMARK_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.model.remark.RemarkList;

/**
 * Deletes a remark from a patient identified using its displayed index from the patient list.
 */
public class DeleteRemarkCommand extends DeleteGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_REMARK_INDEX
            + ": Deletes a remark from a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_REMARK_INDEX + " REMARK_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_REMARK_INDEX + " 1";
    public static final String MESSAGE_SUCCESS = "Deleted remark %1$d from %2$s: %3$s";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index remarkIndex;

    /**
     * Creates an DeleteRemarkCommand to delete a Remark from the specified patient.
     *
     * @param patientIndex The index of the patient in the filtered person list to delete the remark.
     * @param remarkIndex The index of the remark in the patient's remark list.
     */
    public DeleteRemarkCommand(Index patientIndex, Index remarkIndex) {
        requireAllNonNull(patientIndex, remarkIndex);

        this.patientIndex = patientIndex;
        this.remarkIndex = remarkIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        RemarkList initialRemarkList = patientToEdit.getRemarks();

        if (remarkIndex.getZeroBased() >= initialRemarkList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMARK_INDEX);
        }

        // RemarkNotFoundException not caught here since the above handles the same error
        RemarkList updatedRemarkList = initialRemarkList.delete(remarkIndex.getZeroBased());
        Remark deletedRemark = initialRemarkList.get(remarkIndex.getZeroBased());

        Patient editedPatient = new Patient(patientToEdit, updatedRemarkList);

        PersonListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_SUCCESS, remarkIndex.getOneBased(),
                editedPatient.getName(), deletedRemark), COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteRemarkCommand)) {
            return false;
        }

        // state check
        DeleteRemarkCommand o = (DeleteRemarkCommand) other;
        return patientIndex.equals(o.patientIndex) && remarkIndex.equals((o.remarkIndex));
    }
}
