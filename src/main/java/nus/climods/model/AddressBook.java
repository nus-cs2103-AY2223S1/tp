package nus.climods.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import nus.climods.model.module.UserModule;
import nus.climods.model.person.Person;
import nus.climods.model.person.UniquePersonList;
import nus.climods.model.module.UniqueUserModuleList;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueUserModuleList modules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        modules = new UniqueUserModuleList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}. {@code persons} must not contain duplicate
     * persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book. The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}. {@code target} must exist in the
     * address book. The person identity of {@code editedPerson} must not be the same as another existing person in the
     * address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }


    //-------------------------UserModule --------------------------------------------------------
    /**
     * Returns true if a module with the same identity as {@code module} exists in the address book.
     */
    public boolean hasUserModule(UserModule module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a module to the address book. The module must not already exist in the address book.
     */
    public void addUserModule(UserModule p) {
        modules.add(p);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedUserModule}. {@code target} must exist in the
     * address book. The module identity of {@code editedUserModule} must not be the same as another existing module in the
     * address book.
     */
    public void setUserModule(UserModule target, UserModule editedUserModule) {
        requireNonNull(editedUserModule);

        modules.setUserModule(target, editedUserModule);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address book.
     */
    public void removeUserModule(UserModule key) {
        modules.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return modules.asUnmodifiableObservableList().size() + " modules";
        // TODO: refine later
    }

    @Override
    public ObservableList<UserModule> getUserModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddressBook // instanceof handles nulls
            && modules.equals(((AddressBook) other).modules));
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }

}
