package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.AddNoteCommandParser;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.note.Note;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddNoteCommandTest {

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null));
    }

    @Test
    public void execute_noteAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingNoteAdded modelStub = new ModelStubAcceptingNoteAdded();
        Note validNote = new NoteBuilder().build();

        CommandResult commandResult = new AddNoteCommand(validNote).execute(modelStub);

        assertEquals(String.format(AddNoteCommand.MESSAGE_SUCCESS, validNote), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validNote), modelStub.notesAdded);

    }

    @Test
    public void execute_duplicateNote_throwsCommandException() {
        Note validNote = new NoteBuilder().build();
        AddNoteCommand addNoteCommand = new AddNoteCommand(validNote);
        ModelStub modelStub = new ModelStubWithNote(validNote);

        assertThrows(CommandException.class, AddNoteCommand.MESSAGE_DUPLICATE_NOTE, () ->
                addNoteCommand.execute(modelStub));
    }

    @Test
    public void execute_addNoteWithTag_addsTagIntoTagMapping() {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertFalse(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));
    }

    // Ensure that if a Note is added with a Tag that already exists in the address book, the
    // Tag that the Note points to is the same object as the Tag that exists in the address book.
    @Test
    public void execute_addNoteWithTag_tagAlreadyExistsInTagMapping() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";
        model.addTag(new Tag(tagName));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        List<Tag> listOfTagsFromNote = new ArrayList<>(model.getAddressBook().getNoteBook().get(0).getTags());
        Tag tagFromNote = listOfTagsFromNote.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromNote);
    }

    // Tag being added already exists in UniqueTagMapping because a Person has it
    @Test
    public void execute_addNoteWithTag_tagAlreadyExistsInTagMappingDueToPerson() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertAll(() -> new AddCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NAME + PersonBuilder.DEFAULT_NAME + " "
                        + CliSyntax.PREFIX_PHONE + PersonBuilder.DEFAULT_PHONE + " "
                        + CliSyntax.PREFIX_EMAIL + PersonBuilder.DEFAULT_EMAIL + " "
                        + CliSyntax.PREFIX_ADDRESS + PersonBuilder.DEFAULT_ADDRESS + " "
                        + CliSyntax.PREFIX_BIRTHDAY + PersonBuilder.DEFAULT_BIRTHDAY + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_TAG + tagName)
                .execute(model));

        List<Tag> listOfTagsFromNotes = new ArrayList<>(model.getAddressBook().getNoteBook().get(0).getTags());
        Tag tagFromNotes = listOfTagsFromNotes.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromNotes);
    }

    @Test
    public void equals() {
        Note meeting = new NoteBuilder().withTitle("Meeting").build();
        Note event = new NoteBuilder().withTitle("Event").build();
        AddNoteCommand addMeetingNoteCommand = new AddNoteCommand(meeting);
        AddNoteCommand addEventNoteCommand = new AddNoteCommand(event);


        // same object -> returns true
        assertTrue(addMeetingNoteCommand.equals(addMeetingNoteCommand));

        // same values -> returns true
        AddNoteCommand addMeetingNoteCommandCopy = new AddNoteCommand(meeting);
        assertTrue(addMeetingNoteCommand.equals(addMeetingNoteCommandCopy));

        // different types -> returns false
        assertFalse(addMeetingNoteCommand.equals(1));

        // null -> returns false
        assertFalse(addMeetingNoteCommand.equals(null));

        // different person -> returns false
        assertFalse(addMeetingNoteCommand.equals(addEventNoteCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteNote(Note target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNote(Note target, Note editedNote) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableMap<String, Tag> getTagMapping() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean notebookContainsTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Note> getFilteredNoteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterPersonListByName(String preamble, String messageUsage, ParseException pe)
                throws ParseException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredNoteList(Predicate<Note> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single existing note.
     */
    private class ModelStubWithNote extends ModelStub {
        private final Note note;

        ModelStubWithNote(Note note) {
            requireNonNull(note);
            this.note = note;
        }

        @Override
        public boolean hasNote(Note note) {
            requireNonNull(note);
            return this.note.isSameNote(note);
        }
    }

    /**
     * A Model stub that always accept the note being added.
     */
    private class ModelStubAcceptingNoteAdded extends ModelStub {
        final ArrayList<Note> notesAdded = new ArrayList<>();

        @Override
        public boolean hasNote(Note note) {
            requireNonNull(note);
            return notesAdded.stream().anyMatch(note::isSameNote);
        }

        @Override
        public void addNote(Note note) {
            requireNonNull(note);
            notesAdded.add(note);
        }

        @Override
        public ObservableMap<String, Tag> getTagMapping() {
            return FXCollections.observableHashMap();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
