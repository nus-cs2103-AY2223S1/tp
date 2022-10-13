package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;
//import seedu.address.model.person.Address;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
//import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final MeetingList meetingList;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Meeting> filteredMeetings;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyMeetingList meetingList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, meetingList, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.meetingList = new MeetingList(meetingList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredMeetings = new FilteredList<>(this.meetingList.getMeetingList());

        // To check

        //        ObservableList<Meeting> internalList = FXCollections.observableArrayList();
        //        ObservableList<Meeting> internalUnmodifiableList =
        //                FXCollections.unmodifiableObservableList(internalList);
        //
        //        ArrayList<Person> myArray = new ArrayList<>();
        //
        //        myArray.add(new Person(new Name("Alex Yeoh"), new Phone("87438807"),
        //                new Email("alexyeoh@example.com"),
        //                new Address("Blk 30 Geylang Street 29, #06-40"),
        //                getTagSet("friends")));
        //
        //        myArray.add(new Person(new Name("Bernice Yu"), new Phone("99272758"),
        //                new Email("berniceyu@example.com"),
        //                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
        //                getTagSet("colleagues", "friends")));
        //        try {
        //            internalList.addAll(this.meetingList.getMeetingList().stream().collect(Collectors.toList()));
        //            internalList.add(new Meeting(myArray, "Study Session", "06-10-2022 2015", "UTown"));
        //            internalList.add(new Meeting(myArray, "Ice Skating", "12-10-2022 2015", "Jurong East"));
        //        } catch (Exception e) {
        //            System.out.println("Exception: " + e);
        //        }
        //
        //        filteredMeetings = new FilteredList<>(internalList);
    }

    public ModelManager() {
        this(new AddressBook(), new MeetingList(), new UserPrefs());
    }

    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
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
    public Path getMeetingListFilePath() {
        return userPrefs.getMeetingListFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setMeetingListFilePath(Path meetingListFilePath) {
        requireNonNull(meetingListFilePath);
        userPrefs.setMeetingListFilePath(meetingListFilePath);
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
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
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

    //=========== Meetings ================================================================================

    @Override
    public void setMeetingList(ReadOnlyMeetingList meetingList) {
        this.meetingList.resetData(meetingList);
    }

    @Override
    public ReadOnlyMeetingList getMeetingList() {
        return meetingList;
    }

    @Override
    public Meeting createNewMeeting(ArrayList<Person> peopleToMeet, String meetingTitle,
                                    String meetingDateAndTime, String meetingLocation) throws ParseException {
        return new Meeting(peopleToMeet, meetingTitle, meetingDateAndTime, meetingLocation);
    }

    @Override
    public void addMeeting(Meeting newMeeting) {
        meetingList.addMeeting(newMeeting);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireAllNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
    }

    public void deleteMeeting(Meeting target) {
        meetingList.removeMeeting(target);
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    //=========== Others ================================================================================

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
                && meetingList.equals(other.meetingList)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredMeetings.equals(other.filteredMeetings);
    }

}
