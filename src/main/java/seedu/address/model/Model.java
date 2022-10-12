package seedu.address.model;

import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered meeting list
     */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Creates a new meeting with a person in the address book.
     *
     * @param peopleToMeet the contacts in the address book to create a meeting with
     * @param meetingTitle the title/ description of meeting
     * @param meetingDateAndTime the date and time of meeting
     * @param meetingLocation the location of meeting
     * @return a new meeting
     */
    Meeting createNewMeeting(ArrayList<Person> peopleToMeet, String meetingTitle,
                             String meetingDateAndTime, String meetingLocation) throws ParseException;

    /**
     * Checks if a meeting exists in the address book.
     *
     * @param meeting the new meeting to check
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Adds a new meeting to the address book.
     *
     * @param newMeeting the new meeting to add to address book
     */
    void addMeeting(Meeting newMeeting);

    /**
     * Updates the filter of the filtered meeting list to filter by implementation.
     *
     * @throws NullPointerException if {@param predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    void deleteMeeting(Meeting target);

}
