package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to false
     */
    Predicate<Person> PREDICATE_SHOW_NO_PERSONS = unused -> false;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to false
     */
    Predicate<Internship> PREDICATE_SHOW_NO_INTERNSHIPS = unused -> false;

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
     * Returns the user prefs' InterNUS file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' InterNUS file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces InterNUS data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the instance of InterNUS.
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in InterNUS.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in InterNUS.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in InterNUS.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in InterNUS.
     * The person identity of {@code editedPerson} must not be the same as another existing person in InterNUS.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Refreshes person list.
     */
    void refreshPersonList();

    /**
     * Sorts the filter of the filtered person list by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortPersonList(Comparator<Person> comparator);

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in InterNUS.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given internship.
     * The internship must exist in InterNUS.
     */
    void deleteInternship(Internship target);

    /**
     * Adds the given internship.
     * {@code internship} must not already exist in InterNUS.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in InterNUS.
     * The internship identity of {@code editedInternship}
     * must not be the same as another existing internship in InterNUS.
     */
    void setInternship(Internship target, Internship editedInternship);

    /**
     * Returns an unmodifiable view of the filtered internship list.
     */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Updates the filter of the filtered internship list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);

    /**
     * Refreshes internship list.
     */
    void refreshInternshipList();

    /**
     * Sorts the filtered internship list by the given {@code comparator}.
     */
    void sortInternshipList(Comparator<Internship> comparator);

    /**
     * Gets a unique Id to assign to a newly created Person
     * and increments the Id counter to avoid duplicate Ids.
     *
     * @return A unique Id for a newly created Person.
     */
    int getNextPersonId();

    /**
     * Gets a unique Id to assign to a newly created Internship
     * and increments the Id counter to avoid duplicate Ids.
     *
     * @return A unique Id for a newly created Internship.
     */
    int getNextInternshipId();

    Person findPersonById(PersonId personId);

    Internship findInternshipById(InternshipId internshipId);
}
