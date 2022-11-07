package seedu.clinkedin.logic.commands;

import static seedu.clinkedin.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.person.exceptions.DuplicateNoteException;

/**
 * Changes the note of an existing person in the address book.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an optional note to the person identified"
            + "by the index number in the address book.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "addNote INDEX note/NOTE\n\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "note/Strong in Java";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists";

    private final Index index;
    private final Note note;

    /**
     * @param index of the person in the filtered person list to edit the note
     * @param note  note of the person to be updated to
     */
    public AddNoteCommand(Index index, Note note) {
        requireAllNonNull(index, note);
        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUpdate = lastShownList.get(index.getZeroBased());

        Note updatedNote;
        try {
            updatedNote = personToUpdate.mergeNote(note);
        } catch (DuplicateNoteException d) {
            throw new CommandException(d.getMessage());
        }

        UniqueTagTypeMap tagMap = new UniqueTagTypeMap();
        tagMap.setTagTypeMap(personToUpdate.getTags());
        Person updatedPerson = new Person(personToUpdate.getName(), personToUpdate.getPhone(),
                personToUpdate.getEmail(), personToUpdate.getAddress(), tagMap, personToUpdate.getStatus(),
                updatedNote, personToUpdate.getRating(), personToUpdate.getLinks());

        if (!personToUpdate.isSamePerson(updatedPerson) && model.hasPerson(updatedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToUpdate, updatedPerson);

        return new CommandResult(String.format(MESSAGE_ADD_NOTE_SUCCESS, updatedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddNoteCommand)) {
            return false;
        }

        // state check
        AddNoteCommand e = (AddNoteCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
}
