package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_AMBIGUOUS_NAME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NAME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NON_POSITIVE_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Note> filteredNotes;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredNotes = new FilteredList<>(this.addressBook.getNoteBook());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return addressBook.hasNote(note);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteNote(Note target) {
        addressBook.removeNote(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addNote(Note note) {
        addressBook.addNote(note);
    }


    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        addressBook.setNote(target, editedNote);
    }

    @Override
    public void addTag(Tag tag) {
        addressBook.addTag(tag);
    }

    @Override
    public void removeTag(Tag tag) {
        addressBook.removeTag(tag);
    }

    @Override
    public ObservableMap<String, Tag> getTagMapping() {
        return addressBook.getTagMapping();
    }

    @Override
    public boolean notebookContainsTag(Tag tag) {
        return addressBook.notebookContainsTag(tag);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Note List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Note} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return filteredNotes;
    }

    @Override
    public void updateFilteredNoteList(Predicate<Note> predicate) {
        requireNonNull(predicate);
        filteredNotes.setPredicate(predicate);
    }

    /**
     * Filters the {@code ObservableList<Person>} by person name, originating from
     * some command
     * @param preamble the name to search for, by complete word
     * @param messageUsage the usage of the command this call originated from
     * @param pe the ParseException to throw on failure
     * @throws ParseException if there is nobody found by the find command, or there exist
     *      an ambiguity
     */
    @Override
    public void filterPersonListByName(String preamble, String messageUsage,
                                        ParseException pe) throws ParseException {
        try {
            if (Integer.parseInt(preamble) <= 0) {
                throw new ParseException(MESSAGE_INVALID_NON_POSITIVE_INDEX, pe);
            }
        } catch (NumberFormatException e) {
            if (!Name.isValidName(preamble)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        messageUsage), pe);
            }
        }

        try {
            new FindCommandParser().parse(preamble).execute(this);
        } catch (ParseException ignored) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    messageUsage), pe);
        }

        ObservableList<Person> filteredPersonList = getFilteredPersonList();

        String splitPreamble = Arrays.stream(preamble.split(" "))
                .map(x -> "\"" + x.trim() + "\"")
                .collect(Collectors.joining(" or "));

        if (filteredPersonList.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_NAME, splitPreamble), pe);
        } else if (filteredPersonList.size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_AMBIGUOUS_NAME, splitPreamble), pe);
        }
    }


    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
