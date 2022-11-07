package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_TAG_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.tag.TagList;

/**
 * Deletes a tag from a patient identified using its displayed index from the patient list.
 */
public class DeleteTagCommand extends DeleteGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_TAG_INDEX
            + ": Delete a tags from a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_TAG_INDEX + " TAG_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_TAG_INDEX + " 1";
    public static final String MESSAGE_SUCCESS = "Deleted tag %1$d from %2$s: %3$s";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index tagIndex;

    /**
     * Creates an DeleteTagCommand to delete a tag from the specified patient.
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

        TagList initialTagList = patientToEdit.getTags();

        if (tagIndex.getZeroBased() >= initialTagList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_INDEX);
        }

        // TagNotFoundException not caught here since the above handles the same error
        TagList updatedTagList = initialTagList.delete(tagIndex.getZeroBased());
        Tag deletedTag = initialTagList.get(tagIndex.getZeroBased());

        Patient editedPatient = new Patient(patientToEdit, updatedTagList);

        PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_SUCCESS, tagIndex.getOneBased(),
                editedPatient.getName(), deletedTag), COMMAND_TYPE, personListTracker);
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
        DeleteTagCommand o = (DeleteTagCommand) other;
        return patientIndex.equals(o.patientIndex) && tagIndex.equals((o.tagIndex));
    }
}
