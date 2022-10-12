package longtimenosee.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import longtimenosee.commons.core.GuiSettings;
import longtimenosee.model.client.Client;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.Policy;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Person> PREDICATE_SHOW_NO_PERSONS = unused -> false;

    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;
    Predicate<Client> PREDICATE_SHOW_NO_CLIENTS = unused -> true;

    Predicate<Policy> PREDICATE_SHOW_ALL_POLICIES = unused -> true;
    Predicate<Policy> PREDICATE_SHOW_NO_POLICIES = unused -> true;

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
     * Sorts the list
     * @param comparator comparator used to sort the list
     */
    void sort(Comparator<Person> comparator);

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


    // Colin's versions

    /**
     * Returns true if a person with the same identity as {@code Client} exists in the address book.
     */
    boolean hasClient(Client toAdd);

    /**
     * Adds the client specified.
     */
    void addClient(Client toAdd);

    /**
     * Deletes the given Client.
     * The client must exist in the address book.
     */
    void deleteClient(Client toDelete);

    /**
     * Replaces the given Client {@code target} with {@code editedClient}.
     * {@code client} must exist in the address book.
     * The person identity of {@code editedClient} must not be the same as another existing cleint in the address book.
     */
    void setClient(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    //Policy implementation

    /**
     * Returns true if a policy with the same identity as {@code Policy} exists in the address book.
     */
    boolean hasPolicy(Policy toAdd);

    /**
     * Adds the policy specified.
     */
    void addPolicy(Policy toAdd);

    /**
     * Deletes the given Policy.
     * The policy must exist in the address book.
     */
    void deletePolicy(Policy toDelete);

    /**
     * Replaces the given Policy {@code target} with {@code editedPolicy}.
     * {@code policy} must exist in the address book.
     * The policy of {@code editedPolicy} must not be the same as another existing policy in the address book.
     */
    void setPolicy(Policy target, Policy editedPolicy);

    /** Returns an unmodifiable view of the filtered policy list */
    ObservableList<Policy> getFilteredPolicyList();

    /**
     * Updates the filter of the filtered policy list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPolicyList(Predicate<Policy> predicate);

}
