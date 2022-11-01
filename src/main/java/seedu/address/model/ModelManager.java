package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static javafx.collections.FXCollections.unmodifiableObservableList;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    // Value 0 corresponding to the index of home status in observable list.
    private static final int INDEX_OF_HOME_STATUS = 0;
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    // Single observable boolean with ObservableList as wrapper.
    private ObservableList<Boolean> isHome = FXCollections.observableArrayList(true);
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Module> filteredModules;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredModules = new FilteredList<>(this.addressBook.getModuleList());
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
    public boolean hasPersonInFilteredList(Person person) {
        requireNonNull(person);
        return filteredPersons.stream().anyMatch(person::isSamePerson);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public Person getPersonUsingName(Name nameOfPersonToGet, boolean isFiltered) {
        requireAllNonNull(nameOfPersonToGet, isFiltered);
        Person personWithProvidedName = new Person(nameOfPersonToGet);
        boolean isPersonWithSameNameInFilteredList = hasPersonInFilteredList(personWithProvidedName);
        boolean isPersonWithSameNameInAddressBook = hasPerson(personWithProvidedName);

        if (isFiltered && isPersonWithSameNameInFilteredList) {
            // There should only be one person in the filtered person list with the same name.
            assert countOfPersonsInFilteredListWithSameName(personWithProvidedName) == 1;
            return getPersonInFilteredListWithSameName(personWithProvidedName);
        } else if (!isFiltered && isPersonWithSameNameInAddressBook) {
            return addressBook.getPerson(personWithProvidedName);
        } else {
            throw new PersonNotFoundException();
        }
    }

    private long countOfPersonsInFilteredListWithSameName(Person person) {
        return filteredPersons.stream()
                .filter(person::isSamePerson).count();
    }

    private Person getPersonInFilteredListWithSameName(Person person) {
        return filteredPersons.stream()
                .filter(person::isSamePerson)
                .findFirst().get();
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return addressBook.hasModule(module);
    }

    @Override
    public boolean hasModuleInFilteredList(Module module) {
        requireNonNull(module);
        return filteredModules.stream().anyMatch(module::isSameModule);
    }

    @Override
    public void deleteModule(Module target) {
        addressBook.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        addressBook.addModule(module);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        addressBook.setModule(target, editedModule);
    }

    @Override
    public Module getModuleUsingModuleCode(ModuleCode moduleCodeOfModuleToGet,
                                           boolean isFiltered) {
        requireAllNonNull(moduleCodeOfModuleToGet, isFiltered);
        Module moduleWithProvidedModuleCode = new Module(moduleCodeOfModuleToGet);
        boolean isModuleWithSameModuleCodeInFilteredList = hasModuleInFilteredList(moduleWithProvidedModuleCode);
        boolean isModuleWithSameModuleCodeInAddressBook = hasModule(moduleWithProvidedModuleCode);

        if (isFiltered && isModuleWithSameModuleCodeInFilteredList) {
            // There should only be one module in the filtered module list with the same module code.
            assert countOfModulesInFilteredListWithSameModuleCode(moduleWithProvidedModuleCode) == 1;
            return getModuleInFilteredListWithSameModuleCode(moduleWithProvidedModuleCode);
        } else if (!isFiltered && isModuleWithSameModuleCodeInAddressBook) {
            return addressBook.getModule(moduleWithProvidedModuleCode);
        } else {
            throw new ModuleNotFoundException();
        }
    }

    private long countOfModulesInFilteredListWithSameModuleCode(Module module) {
        return filteredModules.stream()
                .filter(module::isSameModule).count();
    }

    private Module getModuleInFilteredListWithSameModuleCode(Module module) {
        return filteredModules.stream()
                .filter(module::isSameModule)
                .findFirst().get();
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

    //=========== Filtered Module List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
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
        return isHome.equals(other.isHome)
                && addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredModules.equals(other.filteredModules);
    }

    //=========== Navigation-related Methods =============================================================
    @Override
    public ObservableList<Boolean> getHomeStatus() {
        assert isHome.size() == 1;
        // Return an unmodifiable copy.
        return unmodifiableObservableList(isHome);
    }

    @Override
    public Boolean getHomeStatusAsBoolean() {
        assert isHome.size() == 1;
        return isHome.get(INDEX_OF_HOME_STATUS);
    }

    @Override
    public void setHomeStatus(boolean isHome) {
        assert this.isHome.size() == 1;
        this.isHome.set(INDEX_OF_HOME_STATUS, isHome);
    }

    @Override
    public void goToHomePage() {
        assert isHome.size() == 1;
        isHome.set(INDEX_OF_HOME_STATUS, true);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
}
