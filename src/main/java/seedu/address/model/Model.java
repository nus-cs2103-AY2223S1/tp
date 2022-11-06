package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    ArrayList<Name> CURRENT_NAMES = new ArrayList<>();
    Predicate<Person> CURRENT_PREDICATE = (person) -> CURRENT_NAMES.contains(person.getName());

    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

    /** {@code Comparator} that ungroup persons by their tags */
    Comparator<Person> COMPARATOR_UNGROUP_PATIENTS = (p1, p2) -> p1.compareTo(p2);

    /** {@code Predicate} that ungroup appointments by their tags */
    Comparator<Appointment> COMPARATOR_UNGROUP_APPOINTMENTS = (a1, a2) -> a1.compareTo(a2);

    /** {@code Comparator} that group persons by their tags */
    Comparator<Person> COMPARATOR_GROUP_PATIENTS = (p1, p2) -> p1.groupCompareTo(p2);

    /** {@code comparator} that group appointments by their tags */
    Comparator<Appointment> COMPARATOR_GROUP_TAG_APPOINTMENTS = (a1, a2) -> a1.groupCompareTo(a2, Key.TAG);

    /** {@code comparator} that group appointments by their patients */
    Comparator<Appointment> COMPARATOR_GROUP_PATIENT_APPOINTMENTS = (a1, a2) -> a1.groupCompareTo(a2, Key.PATIENT);

    /** {@code comparator} that group appointments by their mark status */
    Comparator<Appointment> COMPARATOR_GROUP_MARK_APPOINTMENTS = (a1, a2) -> a1.groupCompareTo(a2, Key.MARK);

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
     * Adds the given appointment.
     * {@code appointment} must not already exist in the address book.
     */
    void addAppointment(Appointment appointment);
    /**
     * Deletes the given appointment.
     * {@code target} must already exist in the address book.
     */
    void deleteAppointment(Appointment target);
    /**
     * Deletes the given appointment list.
     * {@code appointments} must already exist in the address book.
     */
    void deleteAppointments(List<Appointment> appointments);
    /**
     * Updates the given appointment.
     * {@code target} must already exist in the address book.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

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
     * Returns an unmodifiable view of the filtered appointment list.
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /**
     * Updates the comparator of the filtered person list to filter by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updatePersonComparator(Comparator<Person> comparator);

    /**
     * Updates the comparator of the filtered appointment list to filter by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateAppointmentComparator(Comparator<Appointment> comparator);

    /**
     * Updates the filter of the filtered person and appointment lists by the given {@code predicate}
     * and {@code appointmentPredicate}.
     * @throws NullPointerException if {@code predicate} or {@code appointmentPredicate} is null.
     */
    void updateFilteredLists(Predicate<Person> predicate, Predicate<Appointment> appointmentPredicate);
}
