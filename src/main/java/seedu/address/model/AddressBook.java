package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
    private final UniqueCommissionList commissions;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        customers = new UniqueCustomerList();
        commissions = new UniqueCommissionList();
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
    }

    public void setCommissions(List<Commission> commissions) {
        this.commissions.setCommissions(commissions);
    }
    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setCustomers(newData.getCustomerList());
        setCommissions(newData.getCommissionList());
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
        // Assumption: addCommission is NOT CALLED before adding the customer to the list
        // Otherwise, there will be duplicates in commissions
        p.getCommissions().forEach(commissions::add);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer in the
     * address book.
     */
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);
        // Assumption: edits to commissions should already call the commission-level operations.
        // Otherwise, combined commission list will not be updated correctly.
        customers.setCustomer(target, editedCustomer);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCustomer(Customer key) {
        key.getCommissions().forEach(commissions::remove);
        customers.remove(key);
    }

    // commission-level operations

    /**
     * Returns true if a commission with the same identity as {@code commission} exists in the customer's
     * commission list.
     */
    public boolean hasCommission(Customer customer, Commission commission) {
        requireAllNonNull(customer, commission);
        return getUniqueCommissionList(customer).contains(commission);
    }

    /**
     * Adds a commission to the address book.
     * The commission must not already exist in the customer's commission list.
     */
    public void addCommission(Customer customer, Commission commission) {
        requireAllNonNull(customer, commission);
        getUniqueCommissionList(customer).add(commission);
        commissions.add(commission);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedCommission}.
     * {@code target} must exist in the address book.
     * The commission identity of {@code editedCommission} must not be the same as another existing commission in the
     * customer's commission list.
     */
    public void setCommission(Customer customer, Commission target, Commission editedCommission) {
        requireAllNonNull(customer, editedCommission);
        getUniqueCommissionList(customer).setCommission(target, editedCommission);
        commissions.setCommission(target, editedCommission);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the customer's commission list.
     */
    public void removeCommission(Customer customer, Commission key) {
        requireAllNonNull(customer, key);
        getUniqueCommissionList(customer).remove(key);
        commissions.remove(key);
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
    public ObservableList<Commission> getCommissionList() {
        return commissions.asUnmodifiableObservableList();
    }

    public UniqueCommissionList getUniqueCommissionList(Customer customer) {
        return customer.getCommissions();
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
