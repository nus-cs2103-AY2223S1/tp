package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupOutOfBoundException;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonOutOfBoundException;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.TaskOutOfBoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final AddressBookParser addressBookParser;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Group> filteredTeams;
    private Optional<AbstractSingleItem> currentContext = Optional.empty();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.addressBookParser = AddressBookParser.get();
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTeams = new FilteredList<>(this.addressBook.getTeamsList());
        filteredTasks = new FilteredList<>(this.addressBook.getTasksList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    /**
     * Updates all gui
     */
    @Override
    public void refresh() {
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_GROUPS);
    }

    // =========== UserPrefs
    // ==================================================================================

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

    // =========== AddressBook
    // ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    //// person level methods and accessors

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
        updateFilteredPersonList(List.of());
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //// group level methods and accessors

    @Override
    public boolean hasTeam(Group grp) {
        requireNonNull(grp);
        return addressBook.hasGroup(grp);
    }

    @Override
    public void deleteTeam(Group target) {
        // delete all subteams
        addressBook.removeTeamIf(grp -> grp.isPartOfContext(target));
        addressBook.removeTaskIf(tsks -> tsks.isPartOfContext(target));
        addressBook.forEachPerson(p -> p.removeParent(target));
        addressBook.removeTeam(target);
    }

    @Override
    public void addTeam(Group grp) {
        addressBook.addTeam(grp);
        updateFilteredTeamList(List.of());
    }

    //// task level methods and accessors

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return addressBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        requireNonNull(task);
        addressBook.removeTask(task);
    }

    @Override
    public void addTask(Task task) {
        addressBook.addTask(task);
        updateFilteredTaskList(List.of());
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        addressBook.setTask(target, editedTask);
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    // =========== Filtered Person List Accessors
    // =============================================================

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
        if (predicate == null) {
            updateFilteredPersonList(List.of());
            return;
        }
        updateFilteredPersonList(List.of(predicate));
    }

    @Override
    public void updateFilteredPersonList(List<Predicate<Person>> predicates) {
        requireNonNull(predicates);
        Predicate<Person> predicate = p -> {
            return currentContext.map(cxt -> p.isPartOfContext(cxt)).orElse(true)
                && predicates.stream().map(pred -> pred.test(p)).allMatch(res -> res == true);
        };

        filteredPersons.setPredicate(predicate);
    }

    @Override
    public Person getFromFilteredPerson(Index index) throws PersonOutOfBoundException {
        requireNonNull(index);
        List<Person> filteredList = getFilteredPersonList();
        int indexNum = index.getZeroBased();
        if (filteredList.isEmpty() || indexNum < 0 || indexNum >= filteredList.size()) {
            throw new PersonOutOfBoundException(filteredList.size(), indexNum + 1);
        }
        return filteredList.get(indexNum);
    }

    // =========== Filtered Teams List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Group> getFilteredTeamList() {
        return filteredTeams;
    }

    @Override
    public void updateFilteredTeamList(Predicate<Group> predicate) {
        if (predicate == null) {
            updateFilteredTeamList(List.of());
            return;
        }
        updateFilteredTeamList(List.of(predicate));
    }

    @Override
    public void updateFilteredTeamList(List<Predicate<Group>> predicates) {
        requireNonNull(predicates);
        Predicate<Group> predicate = g -> {
            return currentContext.map(cxt -> g.isPartOfContext(cxt)).orElse(true)
                && predicates.stream().map(pred -> pred.test(g)).allMatch(res -> res == true);
        };

        filteredTeams.setPredicate(predicate);
    }

    @Override
    public Group getFromFilteredTeams(Index index) throws GroupOutOfBoundException {
        requireNonNull(index);
        List<Group> filteredList = getFilteredTeamList();
        int indexNum = index.getZeroBased();
        if (filteredList.isEmpty() || indexNum < 0 || indexNum >= filteredList.size()) {
            throw new GroupOutOfBoundException(filteredList.size(), indexNum + 1);
        }
        return filteredList.get(indexNum);
    }

    // filtered tasks list accessors ========
    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        if (predicate == null) {
            updateFilteredTaskList(List.of());
            return;
        }
        updateFilteredTaskList(List.of(predicate));
    }

    @Override
    public void updateFilteredTaskList(List<Predicate<Task>> predicates) {
        requireNonNull(predicates);
        Predicate<Task> predicate = t -> {
            return currentContext.map(cxt -> t.isPartOfContext(cxt)).orElse(true)
                && predicates.stream().map(pred -> pred.test(t)).allMatch(res -> res == true);
        };

        filteredTasks.setPredicate(predicate);
    }

    @Override
    public Task getFromFilteredTasks(Index index) throws TaskOutOfBoundException {
        requireNonNull(index);
        List<Task> filteredList = getFilteredTaskList();
        int indexNum = index.getZeroBased();
        if (filteredList.isEmpty() || indexNum < 0 || indexNum >= filteredList.size()) {
            throw new TaskOutOfBoundException(filteredList.size(), indexNum + 1);
        }
        return filteredList.get(indexNum);
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
            && filteredPersons.equals(other.filteredPersons)
            && filteredTeams.equals(other.filteredTeams);
    }

    @Override
    public void updateContextContainer(AbstractSingleItem container) {
        currentContext = Optional.ofNullable(container);
        updateFilteredPersonList(List.of());
        updateFilteredTeamList(List.of());
        updateFilteredTaskList(List.of());
    }

    @Override
    public AbstractSingleItem getContextContainer() {
        return currentContext.orElse(null);
    }
}
