package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /** {@code Predicate} that always evaluate to false */
    Predicate<Person> PREDICATE_SHOW_ZERO_PERSON = unused -> false;
    Predicate<Module> PREDICATE_SHOW_ZERO_MODULE = unused -> false;

    //// person-related methods

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

    /** Returns the AddressBook */
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

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a person with the same identity as {@code person}
     * exists in the filtered list of persons being displayed to the user.
     */
    boolean hasPersonInFilteredList(Person person);

    /**
     * Searches for and retrieves a person currently present in the address
     * book using the name. Depending on the {@code isFiltered}
     * parameter, we can choose to search in the full list of persons in the
     * address book or only search from the current filtered list of persons
     * being displayed to the user.
     * @param nameOfPersonToGet {@code Name} of the person we would
     *                                like to search for and retrieve.
     * @param isFiltered {@boolean} value of whether the search should be
     *                   conducted in the full list or filtered list.
     * @return {@code Person} with the specified {@code Name} that is in the address book.
     */
    Person getPersonUsingName(Name nameOfPersonToGet, boolean isFiltered) throws ModuleNotFoundException;

    //// module-related methods

    /**
     * Returns true if a module with the same identity as {@code module} exists in Plannit,
     * and false otherwise.
     */
    boolean hasModule(Module module);

    /**
     * Returns true if a module with the same identity as {@code module}
     * exists in the filtered list of modules being displayed to the user.
     */
    boolean hasModuleInFilteredList(Module module);

    /**
     * Deletes the given module.
     * The module must exist in the address book.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in Plannit.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the address book.
     * The module given in {@code editedModule} must not be the same as
     * another existing module in the address book.
     */
    void setModule(Module target, Module editedModule);

    void updateFilteredModuleList(Predicate<Module> predicate);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Searches for and retrieves a module currently present in the address
     * book using the module code. Depending on the {@code isFiltered}
     * parameter, we can choose to search in the full list of modules in the
     * address book or only search from the current filtered list of modules
     * being displayed to the user.
     * @param moduleCodeOfModuleToGet {@code ModuleCode} of the module we would
     *                                like to search for and retrieve.
     * @param isFiltered {@boolean} value of whether the search should be
     *                   conducted in the full list or filtered list.
     * @return {@code Module} with the specified {@code ModuleCode} that is in the address book.
     */
    Module getModuleUsingModuleCode(ModuleCode moduleCodeOfModuleToGet,
                                    boolean isFiltered) throws ModuleNotFoundException;

    //=========== Navigation-related Methods =============================================================
    /**
     * Returns ObservableList singleton with true if Plannit is at the home
     * page, false otherwise.
     */
    ObservableList<Boolean> getHomeStatus();

    /**
     * Returns Boolean that is true if Plannit is at the home
     * page, false otherwise.
     */
    Boolean getHomeStatusAsBoolean();

    /**
     * Replaces home status with {@code isHome}.
     * @param isHome The current status of Plannit
     */
    void setHomeStatus(boolean isHome);

    /**
     * Navigates user to home page.
     */
    void goToHomePage();
}
