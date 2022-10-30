package seedu.boba.model;

import javafx.collections.ObservableList;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.customer.UniqueCustomerList;
import seedu.boba.model.customer.exceptions.PersonNotFoundException;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class BobaBot implements ReadOnlyBobaBot {

    private final UniqueCustomerList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */ {
        persons = new UniqueCustomerList();
    }

    public BobaBot() {
    }

    /**
     * Creates an BobaBot using the Persons in the {@code toBeCopied}
     */
    public BobaBot(ReadOnlyBobaBot toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the customer list with {@code customers}.
     * {@code customers} must not contain duplicate customers.
     */
    public void setPersons(List<Customer> customers) {
        this.persons.setPersons(customers);
    }

    /**
     * Resets the existing data of this {@code BobaBot} with {@code newData}.
     */
    public void resetData(ReadOnlyBobaBot newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// customer-level operations

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    public boolean hasPerson(Customer customer) {
        requireNonNull(customer);
        return persons.contains(customer);
    }

    /**
     * Adds a customer to the address book.
     * The customer must not already exist in the address book.
     */
    public void addPerson(Customer p) {
        persons.add(p);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer
     * in the address book.
     */
    public void setPerson(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);

        persons.setPerson(target, editedCustomer);
    }

    /**
     * Removes {@code key} from this {@code BobaBot}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Customer key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Customer> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BobaBot // instanceof handles nulls
                && persons.equals(((BobaBot) other).persons));
    }

    /**
     * Compares if two BobaBot ledgers are strictly equals
     *
     * @param other
     * @return
     */
    public boolean strictlyEquals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BobaBot // instanceof handles nulls
                && persons.strictlyEquals(((BobaBot) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    /**
     * Returns the index of the customer with the same phone number.
     *
     * @param phone Phone number to search
     * @return index of the customer with the same phone number
     * @throws PersonNotFoundException if no customer with corresponding phone number found
     */
    public int findNum(Phone phone) throws PersonNotFoundException {
        return persons.findNum(phone);
    }

    /**
     * Returns the index of the customer with the same email.
     *
     * @param email Email to search
     * @return index of the customer with the same email
     * @throws PersonNotFoundException if no customer with corresponding email found
     */
    public int findEmail(Email email) throws PersonNotFoundException {
        return persons.findEmail(email);
    }

    /**
     * Returns the current Reward points of a Customer
     *
     * @param phone Phone number of the Customer of interest
     * @return the current Reward points of a Customer
     */
    public Reward getCurrentReward(Phone phone) {
        return persons.getCurrentReward(phone);
    }

    /**
     * Returns the current Reward points of a Customer
     *
     * @param email Email of the Customer of interest
     * @return the current Reward points of a Customer
     */
    public Reward getCurrentReward(Email email) {
        return persons.getCurrentReward(email);
    }
}
