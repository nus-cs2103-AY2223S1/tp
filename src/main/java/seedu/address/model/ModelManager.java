package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Session;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    public static final String MESSAGE_NO_NEXT_SESSION_FOUND = "Don't worry, no next session for you this week!";
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    private boolean fullView;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        fullView = false;
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

    @Override
    public void setFullView() {
        fullView = true;
    }

    @Override
    public void setListView() {
        fullView = false;
        addressBook.setAllToListView();
    }

    @Override
    public boolean isFullView() {
        return fullView;
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

    public String getNextSession() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE HH:mm")
                .withResolverStyle(ResolverStyle.STRICT);
        String timeNow = LocalDateTime.now().format(dtf);
        Session nowSession = new Session(timeNow);
        HashMap<Session, Person> sessionPersonHashMap = new HashMap<>();
        ArrayList<Session> compareList = new ArrayList<>();
        this.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        if (getFilteredPersonList().isEmpty()) {
            return MESSAGE_NO_NEXT_SESSION_FOUND;
        }
        this.getFilteredPersonList().forEach(person -> {
            if (!person.getSessionList().sessionList.isEmpty()) {
                for (int i = 0; i < person.getSessionList().sessionList.size(); i++) {
                    Session currSession = person.getSessionList().sessionList.get(i);
                    if (nowSession.compareTo(currSession) <= 0) {
                        compareList.add(currSession);
                        sessionPersonHashMap.put(currSession, person);
                    }
                }
            }
        });
        if (compareList.isEmpty()) {
            return MESSAGE_NO_NEXT_SESSION_FOUND;
        }
        compareList.sort(Session::compareTo);
        String res = "Next Session: " + sessionPersonHashMap.get(compareList.get(0)).getName()
                + " " + compareList.get(0).toString();
        return res;
    }

}
