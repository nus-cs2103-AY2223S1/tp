package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.commands.exceptions.DuplicateEntryException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.tag.exceptions.DuplicateTagException;

/**
 * Adds a tag to an existing patient in the person list.
 */
public class AddTagCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX
            + ": Adds a tag to a patient."
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 1 " + PREFIX_TAG + "fall-risk";
    public static final String MESSAGE_SUCCESS = "New tag added to %1$s: %2$s";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

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
            TagList updatedTagList = patientToEdit.getTags().add(tag);

            Patient editedPatient = new Patient(patientToEdit, updatedTagList);

            PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_SUCCESS, editedPatient.getName(), tag),
                    COMMAND_TYPE, personListTracker);
        } catch (DuplicateTagException dte) {
            model.setPatientOfInterest(patientToEdit);
            throw new DuplicateEntryException(String.format(Messages.MESSAGE_DUPLICATE_TAG, patientToEdit.getName()));
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
