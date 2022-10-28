package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.UniqueCommissionList;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.UniqueCustomerList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameCustomer comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueCustomerList customers;
    private final UniqueCommissionList allCommissions;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        customers = new UniqueCustomerList();
        allCommissions = new UniqueCommissionList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Customers in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the customer list with {@code customers}.
     * {@code customers} must not contain duplicate customers.
     */
    public void setCustomers(List<Customer> customers) {
        this.customers.setCustomers(customers);
        allCommissions.setCommissions(new ArrayList<>());
        customers.forEach(customer -> customer.getCommissionList().forEach(allCommissions::add));
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setCustomers(newData.getCustomerList());
    }

    //// customer-level operations

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return customers.contains(customer);
    }

    /**
     * Adds a customer to the address book.
     * The customer must not already exist in the address book.
     */
    public void addCustomer(Customer p) {
        customers.add(p);
        p.getCommissionList().forEach(allCommissions::add);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer in the
     * address book.
     */
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);
        customers.setCustomer(target, editedCustomer);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCustomer(Customer key) {
        customers.remove(key);
        key.getCommissionList().forEach(allCommissions::remove);
    }


    /**
     * Adds commission to specified customer.
     *
     * @param commission new commission to add.
     */
    public void addCommission(Customer customer, Commission commission) {
        customer.addCommission(commission);
        allCommissions.add(commission);
    }

    /**
     * Replaces the given commission {@code target} in the customer's commission list with {@code editedCommission}.
     * {@code target} must exist in the address book.
     * The commission identity of {@code editedCommission} must not be the same as another existing commission in the
     * customer's commission list.
     */
    public void setCommission(Customer customer, Commission target, Commission editedCommission) {
        customer.setCommission(target, editedCommission);
        allCommissions.setCommission(target, editedCommission);
    }


    /**
     * Removes {@code key} from this {@code Customer}.
     * {@code key} must exist in the customer's commission list.
     */
    public void removeCommission(Customer customer, Commission key) {
        customer.removeCommission(key);
        allCommissions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return customers.asUnmodifiableObservableList().size() + " customers";
        // TODO: refine later
    }

    @Override
    public ObservableList<Customer> getCustomerList() {
        return customers.asUnmodifiableObservableList();
    }


    @Override
    public ObservableList<Commission> getFullCommissionList() {
        return allCommissions.asUnmodifiableObservableList();
    }

    /**
     * Checks if the fields within the addressbook are identical when performing the
     * `isSameCustomer` or `isSameCommission` check.
     */
    public boolean isSameAddressBook(AddressBook other) {
        return customers.isSameUniqueCustomerList(other.customers)
                && allCommissions.isSameUniqueCommissionList(other.allCommissions);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && customers.equals(((AddressBook) other).customers));
    }

    @Override
    public int hashCode() {
        return customers.hashCode();
    }
}
