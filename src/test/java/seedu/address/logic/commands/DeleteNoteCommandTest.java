package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showNoteAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.address.testutil.TypicalNotes.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.AddNoteCommandParser;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.DeleteNoteCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.PersonBuilder;

public class DeleteNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Note noteToDelete = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteNote(noteToDelete);

        assertCommandSuccess(deleteNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(outOfBoundIndex);

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Note noteToDelete = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteNote(noteToDelete);
        showNoNote(expectedModel);

        assertCommandSuccess(deleteNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Index outOfBoundIndex = INDEX_SECOND_NOTE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getNoteBook().size());

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(outOfBoundIndex);

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteNoteCommand deleteFirstNoteCommand = new DeleteNoteCommand(INDEX_FIRST_NOTE);
        DeleteNoteCommand deleteSecondNoteCommand = new DeleteNoteCommand(INDEX_SECOND_NOTE);

        // same object -> returns true
        assertTrue(deleteFirstNoteCommand.equals(deleteFirstNoteCommand));

        // same values -> returns true
        DeleteNoteCommand deleteFirstNoteCommandCopy = new DeleteNoteCommand(INDEX_FIRST_NOTE);
        assertTrue(deleteFirstNoteCommand.equals(deleteFirstNoteCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstNoteCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstNoteCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstNoteCommand.equals(deleteSecondNoteCommand));
    }


    /**
     * Updates {@code model}'s filtered note list to show no notes.
     */
    private void showNoNote(Model model) {
        model.updateFilteredNoteList(n -> false);

        assertTrue(model.getFilteredNoteList().isEmpty());
    }


    @Test
    public void execute_removeLastNoteFromModel_removesTagInMapping() {
        Model model = new ModelManager();
        String tagName = "TagRemovedOnLastNote";
        String titleA = "noteA";
        String titleB = "noteB";

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + titleA + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_TAG + tagName)
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + titleB + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_TAG + tagName)
                .execute(model));

        assertAll(() -> new DeleteNoteCommandParser().parse("2").execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new DeleteNoteCommandParser().parse("1").execute(model));

        assertFalse(model.getTagMapping().containsKey(tagName));
    }

    // Tag is not deleted from UniqueTagMapping when there still exists a Person with that Tag
    @Test
    public void execute_deleteNoteWithTag_doesNotDeletesTagFromTagMapping() {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertAll(() -> new AddCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NAME + PersonBuilder.DEFAULT_NAME + " "
                        + CliSyntax.PREFIX_PHONE + PersonBuilder.DEFAULT_PHONE + " "
                        + CliSyntax.PREFIX_ADDRESS + PersonBuilder.DEFAULT_ADDRESS + " "
                        + CliSyntax.PREFIX_EMAIL + PersonBuilder.DEFAULT_EMAIL + " "
                        + CliSyntax.PREFIX_BIRTHDAY + PersonBuilder.DEFAULT_BIRTHDAY + " "
                        + CliSyntax.PREFIX_TAG + tagName)
                .execute(model));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_TAG + tagName)
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new DeleteNoteCommandParser().parse("1").execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));
    }
}
