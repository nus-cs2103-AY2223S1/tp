package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ModuleCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Lesson;
import seedu.address.model.module.Module;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.user.User;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private Set<Lesson> timetable = new HashSet<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.versionedAddressBook.getPersonList());
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
        this.versionedAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public boolean hasUser() {
        return versionedAddressBook.hasUser();
    }

    @Override
    public void addUser(User user) {
        versionedAddressBook.addUser(user);
    }

    @Override
    public User getUser() {
        return versionedAddressBook.getUser();
    }

    @Override
    public void deleteUser() {
        versionedAddressBook.deleteUser();
    }

    @Override
    public void setUser(User editedUser) {
        requireNonNull(editedUser);

        versionedAddressBook.setUser(editedUser);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedAddressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        versionedAddressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        versionedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        versionedAddressBook.setPerson(target, editedPerson);
    }

    @Override
    public void addLessonToUser(Lesson lesson) throws CommandException {
        versionedAddressBook.addLessonToUser(lesson);
    }

    @Override
    public void removeLessonToUser(Lesson lesson) throws CommandException {
        versionedAddressBook.removeLessonToUser(lesson);
    }

    @Override
    public Set<Lesson> getTimetable() {
        return timetable;
    }

    @Override
    public boolean setTimetable(Set<Lesson> lessons) {
        if (lessons.isEmpty()) {
            return false;
        }
        timetable = lessons;
        return true;
    }

    @Override
    public void nextSem() throws CommandException {
        List<Person> currentPeopleList = getFilteredPersonList();

        for (int i = 0; i < currentPeopleList.size(); i++) {
            Person personToEdit = currentPeopleList.get(i);
            ModuleCommand.EditModuleDescriptor editModuleDescriptor = new ModuleCommand.EditModuleDescriptor();
            editModuleDescriptor.setCurrModules(null);
            editModuleDescriptor.setPlanModules(personToEdit.getPlanModules());
            Set<PreviousModule> updatedPreviousModules = new HashSet<>();
            for (int n = 0; n < personToEdit.getCurrModules().size(); n++) {
                Object currCurrentModule = personToEdit.getCurrModules().toArray()[n];
                if (currCurrentModule instanceof CurrentModule) {
                    CurrentModule currentModule = (CurrentModule) currCurrentModule;
                    updatedPreviousModules.add(currentModule.toPrevModule());
                }
            }
            updatedPreviousModules.addAll(personToEdit.getPrevModules());
            editModuleDescriptor.setPrevModules(updatedPreviousModules);
            Person editedPerson = createEditedPerson(personToEdit, editModuleDescriptor);
            setPerson(personToEdit, editedPerson);
        }

        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        getUser().updatePrevMods();
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             ModuleCommand.EditModuleDescriptor editModuleDescriptor) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Github github = personToEdit.getGithub();
        Set<Tag> tags = personToEdit.getTags();
        Set<CurrentModule> setCurrentModules = editModuleDescriptor.getCurrModules();
        Set<PreviousModule> setPreviousModules = editModuleDescriptor.getPrevModules();
        Set<PlannedModule> setPlannedModules = editModuleDescriptor.getPlanModules();

        return new Person(name, phone, email, address, github, tags, setCurrentModules, setPreviousModules,
                setPlannedModules);
    }

    @Override
    public void commitAddressBook() {
        this.versionedAddressBook.commit();
    }

    @Override
    public boolean canUndoAddressBook() {
        return this.versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return this.versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        this.versionedAddressBook.undo();
    }

    @Override
    public void redoAddressBook() {
        this.versionedAddressBook.redo();
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
        return versionedAddressBook.equals(other.versionedAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
