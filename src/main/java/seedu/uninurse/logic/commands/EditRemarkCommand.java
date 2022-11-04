package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_REMARK_INDEX;
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
 * Edits the details of an existing Remark for a patient.
 */
public class EditRemarkCommand extends EditGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_REMARK_INDEX
            + ": Edits a remark of a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_REMARK_INDEX + " REMARK_INDEX " + PREFIX_REMARK + "REMARK\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 " + PREFIX_OPTION_REMARK_INDEX
            + " 1 " + PREFIX_REMARK + "Allergic to Amoxicillin";

    public static final String MESSAGE_EDIT_REMARK_SUCCESS = "Edited remark %1$d of %2$s:\n"
            + "Before: %3$s\n"
            + "After: %4$s";
    public static final String MESSAGE_NOT_EDITED = "Remark to edit must be provided.";
    public static final String MESSAGE_EDIT_DUPLICATE_REMARK = "Remark already exists in %1$s's remark list.";

    public static final CommandType EDIT_REMARK_COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index remarkIndex;
    private final Remark updatedRemark;

    /**
     * Creates an EditRemarkCommand to edit a {@code Remark} from the specified patient.
     *
     * @param patientIndex of the patient in the filtered patient list to edit.
     * @param remarkIndex of the remark to be edited.
     * @param updatedRemark details to edit for the remark.
     */
    public EditRemarkCommand(Index patientIndex, Index remarkIndex, Remark updatedRemark) {
        requireAllNonNull(patientIndex, remarkIndex, updatedRemark);

        this.patientIndex = patientIndex;
        this.remarkIndex = remarkIndex;
        this.updatedRemark = updatedRemark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        RemarkList initialRemarkList = patientToEdit.getRemarks();

        if (remarkIndex.getZeroBased() >= initialRemarkList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMARK_INDEX);
        }

        Remark initialRemark = initialRemarkList.get(remarkIndex.getZeroBased());

        RemarkList updatedRemarkList;

        try {
            updatedRemarkList = initialRemarkList.edit(remarkIndex.getZeroBased(), updatedRemark);
        } catch (DuplicateRemarkException exception) {
            throw new CommandException(String.format(MESSAGE_EDIT_DUPLICATE_REMARK, patientToEdit.getName()));
        }

        Patient editedPatient = new Patient(patientToEdit, updatedRemarkList);

        PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_EDIT_REMARK_SUCCESS,
                remarkIndex.getOneBased(), editedPatient.getName(), initialRemark,
                updatedRemark), EDIT_REMARK_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRemarkCommand)) {
            return false;
        }

        // state check
        EditRemarkCommand e = (EditRemarkCommand) other;
        return patientIndex.equals(e.patientIndex)
                && remarkIndex.equals(e.remarkIndex)
                && updatedRemark.equals(e.updatedRemark);
    }
}
