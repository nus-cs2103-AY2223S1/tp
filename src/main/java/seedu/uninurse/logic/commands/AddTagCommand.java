package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.tag.exceptions.DuplicateTagException;

/**
 * Adds a tag to an existing patient in the person list.
 */
public class AddTagCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = "Command: Add a tag to a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 1 " + PREFIX_TAG + "high-risk";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "New tag added to %1$s: %2$s";
    public static final CommandType ADD_TAG_COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index index;
    private final Tag tag;

    /**
     * Creates an AddTagCommand to add a tag to the specified person.
     *
     * @param index The index of the person in the filtered person list to add the tag.
     * @param tag The tag of the person to be added to.
     */
    public AddTagCommand(Index index, Tag tag) {
        requireAllNonNull(index, tag);

        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());

        try {
            TagList updatedTagList = patientToEdit.getTags().add(tag);

            Patient editedPatient = new Patient(patientToEdit, updatedTagList);

            PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, editedPatient.getName(), tag),
                    ADD_TAG_COMMAND_TYPE, patientListTracker);
        } catch (DuplicateTagException dte) {
            throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_TAG, patientToEdit.getName()));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand o = (AddTagCommand) other;
        return index.equals(o.index) && tag.equals((o.tag));
    }
}
