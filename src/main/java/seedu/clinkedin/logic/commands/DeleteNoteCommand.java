package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.exceptions.TagTypeNotFoundException;
import seedu.clinkedin.model.tag.exceptions.TagNotFoundException;

/**
 * Deletes notes for a person.
 */
public class DeleteNoteCommand extends Command {
    public static final String COMMAND_WORD = "deletenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the note for the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 ";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted note for the person: %1$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists";

    private final Index targetIndex;

    /**
     * Creates an DeleteNoteCommand to delete the specified {@code Note} of a person at the specified {@code Index}
     */
    public DeleteNoteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        try {
            Person personToUpdate = lastShownList.get(targetIndex.getZeroBased());

            UniqueTagTypeMap tagMap = new UniqueTagTypeMap();
            tagMap.setTagTypeMap(personToUpdate.getTags());
            Person updatedPerson = new Person(personToUpdate.getName(), personToUpdate.getPhone(),
                    personToUpdate.getEmail(), personToUpdate.getAddress(), tagMap,
                    personToUpdate.getStatus(), new Note(""), personToUpdate.getRating(),
                    personToUpdate.getLinks());

            if (!personToUpdate.isSamePerson(updatedPerson) && model.hasPerson(updatedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.setPerson(personToUpdate, updatedPerson);

            return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, updatedPerson));
        } catch (TagTypeNotFoundException | TagNotFoundException t) {
            throw new CommandException(t.getMessage());
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNoteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNoteCommand) other).targetIndex));
    }
}
