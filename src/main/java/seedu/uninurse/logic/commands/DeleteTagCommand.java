package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_TAG_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.tag.TagList;

/**
 * Deletes a tag from a patient identified using its displayed index from the patient list.
 */
public class DeleteTagCommand extends DeleteGenericCommand {
    public static final String MESSAGE_USAGE = "Command: Delete a tag from a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_TAG_INDEX + " TAG_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 1 " + PREFIX_OPTION_TAG_INDEX
            + " 2";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag %1$d from %2$s: %3$s";

    public static final CommandType DELETE_TAG_COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index tagIndex;

    /**
     * Creates an DeleteTagCommand to delete a {@code Tag} from the specified patient.
     *
     * @param patientIndex The index of the patient in the filtered person list to delete the tag.
     * @param tagIndex The index of the tag in the patient's tag list.
     */
    public DeleteTagCommand(Index patientIndex, Index tagIndex) {
        requireAllNonNull(patientIndex, tagIndex);

        this.patientIndex = patientIndex;
        this.tagIndex = tagIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        TagList initialTagList = patientToEdit.getTags();

        if (tagIndex.getZeroBased() >= initialTagList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_INDEX);
        }

        // TagNotFoundException not caught here since the above handles the same error
        TagList updatedTagList = initialTagList.delete(tagIndex.getZeroBased());
        Tag deletedTag = initialTagList.get(tagIndex.getZeroBased());

        Patient editedPatient = new Patient(patientToEdit, updatedTagList);

        PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tagIndex.getOneBased(),
                editedPatient.getName(), deletedTag), DELETE_TAG_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        // state check
        DeleteTagCommand e = (DeleteTagCommand) other;
        return patientIndex.equals(e.patientIndex) && tagIndex.equals((e.tagIndex));
    }
}
