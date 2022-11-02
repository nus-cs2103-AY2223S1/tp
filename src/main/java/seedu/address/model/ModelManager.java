package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(AddressBook.createNewAddressBook(), new UserPrefs());
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

    //========== Team Accessors ===============================================================================
    @Override
    public Team getTeam() {
        return addressBook.getTeam();
    }

    @Override
    public ObjectProperty<Team> getTeamAsProperty() {
        return addressBook.getTeamAsProperty();
    }

    @Override
    public void setTeam(Team teamToSet) {
        addressBook.setTeam(teamToSet);
    }

    @Override
    public void setTeams(List<Team> teams) {
        addressBook.setTeams(teams);
    }

    @Override
    public void addTeam(Team teamToAdd) {
        addressBook.addTeam(teamToAdd);
    }

    @Override
    public void deleteTeam(Team teamToDelete) {
        addressBook.deleteTeam(teamToDelete);
    }

    @Override
    public ObservableList<Team> getTeamList() {
        return addressBook.getTeamList();
    }
    //=========== Link Accessors =============================================================================

    @Override
    public boolean hasLink(Link link) {
        return addressBook.hasLink(link);
    }

    @Override
    public void addLink(Link link) {
        addressBook.addLink(link);
    }

    @Override
    public void setLink(Link target, Link editedLink) {
        addressBook.setLink(target, editedLink);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        addressBook.setTask(target, editedTask);
    }

    @Override
    public void deleteLink(Link link) {
        addressBook.deleteLink(link);
    }

    @Override
    public ObservableList<Link> getLinkList() {
        return addressBook.getTeam().getLinkList();
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

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook} based on the specified predicate.
     */
    @Override
    public ObservableList<Person> getFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        return filteredPersons.filtered(predicate);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<Person> getFilteredMemberList() {
        return addressBook.getTeam().getFilteredMemberList();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return addressBook.getTeam().getFilteredTaskList();
    }

    @Override
    public void updateFilteredMembersList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        addressBook.getTeam().updateFilteredMembersList(predicate);
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        addressBook.getTeam().updateFilteredTaskList(predicate);
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
