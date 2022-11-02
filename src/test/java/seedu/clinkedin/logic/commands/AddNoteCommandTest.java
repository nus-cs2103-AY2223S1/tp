package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.exceptions.DuplicateNoteException;

public class AddNoteCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null, new Note("")));
    }

    @Test
    public void execute_validIndexUnfilteredList_throwsDuplicateNoteException() {
        Note note = new Note("She is strong at Java.");
        AddNoteCommand addNoteCommand = new AddNoteCommand(Index.fromOneBased(1), note);
        assertThrows(DuplicateNoteException.class, () -> addNoteCommand.execute(model));
    }

    @Test
    public void execute_noChangeInValue_throwsDuplicateNoteException() {
        Person personToEdit = model.getFilteredPersonList().get(0);
        AddNoteCommand addNoteCommand = new AddNoteCommand(Index.fromOneBased(1), personToEdit.getNote());
        assertThrows(DuplicateNoteException.class, () -> addNoteCommand.execute(model));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Note note = new Note("She is strong at Java.");
        AddNoteCommand addNoteCommand = new AddNoteCommand(Index.fromOneBased(100), note);
        assertThrows(CommandException.class, () -> addNoteCommand.execute(model));
    }

    @Test
    public void execute_validIndex_success() {
        Note note = new Note("She graduates in 1 year.");
        AddNoteCommand addNoteCommand = new AddNoteCommand(Index.fromOneBased(1), note);
        Person personToEdit = model.getFilteredPersonList().get(0);
        Note newNote = personToEdit.mergeNote(note);
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTagTypeMap(), personToEdit.getStatus(), newNote,
                personToEdit.getRating(), personToEdit.getLinks());
        String expectedMessage = String.format(AddNoteCommand.MESSAGE_ADD_NOTE_SUCCESS, editedPerson);
        expectedModel.setPerson(personToEdit, editedPerson);
        assertCommandSuccess(addNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_sameObject() {
        AddNoteCommand command1 = new AddNoteCommand(Index.fromOneBased(1), new Note("3"));
        assertTrue(command1.equals(command1));

    }

    @Test
    public void equals_diffObjectSameParameters() {
        AddNoteCommand command1 = new AddNoteCommand(Index.fromOneBased(1), new Note("3"));
        AddNoteCommand command2 = new AddNoteCommand(Index.fromOneBased(1), new Note("3"));
        assertTrue(command1.equals(command2));
    }

    @Test
    public void notEqual_null() {
        AddNoteCommand command1 = new AddNoteCommand(Index.fromOneBased(1), new Note("3"));
        assertFalse(command1.equals(null));
    }

    @Test
    public void notEqual_differentType() {
        AddNoteCommand command1 = new AddNoteCommand(Index.fromOneBased(1), new Note("3"));
        assertFalse(command1.equals(5));
    }
}
