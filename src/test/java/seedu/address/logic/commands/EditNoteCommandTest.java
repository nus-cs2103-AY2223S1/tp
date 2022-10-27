package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CLUB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTENT_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CLUB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showNoteAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.address.testutil.TypicalNotes.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.AddNoteCommandParser;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.EditNoteCommandParser;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditNoteDescriptorBuilder;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditNoteCommand.
 */
public class EditNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Note editedNote = new NoteBuilder().build();
        EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder(editedNote).build();
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST_NOTE, descriptor);

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, editedNote);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setNote(model.getFilteredNoteList().get(0), editedNote);

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastNote = Index.fromOneBased(model.getFilteredNoteList().size());
        Note lastNote = model.getFilteredNoteList().get(indexLastNote.getZeroBased());

        NoteBuilder noteInList = new NoteBuilder(lastNote);
        Note editedNote = noteInList.withTitle(VALID_TITLE_MEETING).withContent(VALID_CONTENT_MEETING).build();

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder().withTitle(VALID_TITLE_MEETING)
                .withContent(VALID_CONTENT_MEETING).build();

        EditNoteCommand editNoteCommand = new EditNoteCommand(indexLastNote, descriptor);

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, editedNote);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setNote(lastNote, editedNote);

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST_NOTE, new EditNoteDescriptor());
        Note editedNote = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, editedNote);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Note noteInFilteredList = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        Note editedNote = new NoteBuilder(noteInFilteredList).withTitle(VALID_TITLE_CLUB).build();
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST_NOTE,
                new EditNoteDescriptorBuilder().withTitle(VALID_TITLE_CLUB).build());

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, editedNote);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setNote(model.getFilteredNoteList().get(0), editedNote);

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateNoteUnfilteredList_failure() {
        Note firstNote = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder(firstNote).build();
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_SECOND_NOTE, descriptor);

        assertCommandFailure(editNoteCommand, model, EditNoteCommand.MESSAGE_DUPLICATE_NOTE);
    }

    @Test
    public void execute_duplicateNoteFilteredList_failure() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        // edit note in filtered note list into a duplicate in address book
        Note noteInList = model.getAddressBook().getNoteBook().get(INDEX_SECOND_NOTE.getZeroBased());
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST_NOTE,
                new EditNoteDescriptorBuilder(noteInList).build());

        assertCommandFailure(editNoteCommand, model, EditNoteCommand.MESSAGE_DUPLICATE_NOTE);
    }

    @Test
    public void execute_invalidNoteIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);
        EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder().withTitle(VALID_TITLE_CLUB).build();
        EditNoteCommand editNoteCommand = new EditNoteCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered note list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidNoteIndexFilteredList_failure() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);
        Index outOfBoundIndex = INDEX_SECOND_NOTE;
        // ensures that outOfBoundIndex is still in bounds of address book note list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getNoteBook().size());

        EditNoteCommand editNoteCommand = new EditNoteCommand(outOfBoundIndex,
                new EditNoteDescriptorBuilder().withTitle(VALID_TITLE_CLUB).build());

        assertCommandFailure(editNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editNoteWithTag_addsTagIntoTagMapping() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";
        model.addNote(new NoteBuilder().build());

        assertFalse(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));
    }

    // Ensure that if a Note is edited with a Tag that already exists in the address book, the
    // Tag that the Note points to is the same object as the Tag that exists in the address book.
    @Test
    public void execute_editNoteWithTag_tagAlreadyExistsInTagMapping() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";
        model.addNote(new NoteBuilder().build());
        model.addTag(new Tag(tagName));

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        List<Tag> listOfTagsFromNote = new ArrayList<>(model.getAddressBook().getNoteBook().get(0).getTags());
        Tag tagFromNote = listOfTagsFromNote.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromNote);
    }

    @Test
    public void execute_editNoteWithTag_deletesTagFromTagMapping() {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_TAG + tagName)
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + " ")
                .execute(model));

        assertFalse(model.getTagMapping().containsKey(tagName));
    }

    // Tag being added already exists in UniqueTagMapping because a Person has it
    @Test
    public void execute_editNoteWithTag_tagAlreadyExistsInTagMappingDueToPerson() throws Exception {
        Model model = new ModelManager();
        model.addNote(new NoteBuilder().build());
        String tagName = "Operations";

        assertAll(() -> new AddCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NAME + PersonBuilder.DEFAULT_NAME + " "
                        + CliSyntax.PREFIX_PHONE + PersonBuilder.DEFAULT_PHONE + " "
                        + CliSyntax.PREFIX_EMAIL + PersonBuilder.DEFAULT_EMAIL + " "
                        + CliSyntax.PREFIX_ADDRESS + PersonBuilder.DEFAULT_ADDRESS + " "
                        + CliSyntax.PREFIX_BIRTHDAY + PersonBuilder.DEFAULT_BIRTHDAY + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        List<Tag> listOfTagsFromNote = new ArrayList<>(model.getAddressBook().getNoteBook().get(0).getTags());
        Tag tagFromNote = listOfTagsFromNote.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromNote);
    }

    // Tag is not deleted from UniqueTagMapping when there still exists a Person with that Tag
    @Test
    public void execute_editNoteWithTag_doesNotDeletesTagFromTagMapping() {
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

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + " ")
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));
    }

    @Test
    public void equals() {
        final EditNoteCommand standardCommand = new EditNoteCommand(INDEX_FIRST_NOTE, DESC_MEETING);

        // same values -> returns true
        EditNoteDescriptor copyDescriptor = new EditNoteDescriptor(DESC_MEETING);
        EditNoteCommand commandWithSameValues = new EditNoteCommand(INDEX_FIRST_NOTE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditNoteCommand(INDEX_SECOND_NOTE, DESC_MEETING)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditNoteCommand(INDEX_FIRST_NOTE, DESC_CLUB)));
    }

}
