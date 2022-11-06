package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_TAG_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;

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
import seedu.uninurse.model.tag.exceptions.DuplicateTagException;

/**
 * Edits the details of an existing tag for a patient.
 */
public class EditTagCommand extends EditGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_TAG_INDEX
            + ": Edits a tag of a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_TAG_INDEX + " TAG_INDEX " + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 " + PREFIX_OPTION_TAG_INDEX
            + " 1 " + PREFIX_TAG + "fall-risk";
    public static final String MESSAGE_SUCCESS = "Edited tag %1$d of %2$s:\n"
            + "Before: %3$s\n"
            + "After: %4$s";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index tagIndex;
    private final Tag editedTag;

    /**
     * Creates an EditTagCommand to edit a tag from the specified patient.
     *
     * @param patientIndex The index of the patient in the filtered patient list to edit.
     * @param tagIndex The index of the tag in the patient's tag list.
     * @param editedTag The edited tag.
     */
    public EditTagCommand(Index patientIndex, Index tagIndex, Tag editedTag) {
        requireAllNonNull(patientIndex, tagIndex, editedTag);

        this.patientIndex = patientIndex;
        this.tagIndex = tagIndex;
        this.editedTag = editedTag;
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


        try {
            Tag initialTag = initialTagList.get(tagIndex.getZeroBased());
            TagList updatedTagList = initialTagList.edit(tagIndex.getZeroBased(), editedTag);

            Patient editedPatient = new Patient(patientToEdit, updatedTagList);

            PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_SUCCESS, tagIndex.getOneBased(),
                    editedPatient.getName(), initialTag, editedTag), COMMAND_TYPE, personListTracker);
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
        if (!(other instanceof EditTagCommand)) {
            return false;
        }

        // state check
        EditTagCommand o = (EditTagCommand) other;
        return patientIndex.equals(o.patientIndex)
                && tagIndex.equals(o.tagIndex)
                && editedTag.equals(o.editedTag);
    }
}
