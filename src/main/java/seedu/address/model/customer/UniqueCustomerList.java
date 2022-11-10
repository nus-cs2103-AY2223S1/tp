package seedu.address.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.customer.exceptions.CustomerNotFoundException;
import seedu.address.model.customer.exceptions.DuplicateCustomerException;

/**
 * A list of customers that enforces uniqueness between its elements and does not allow nulls.
 * A customer is considered unique by comparing using {@code Customer#isSameCustomer(Customer)}. As such, adding and
 * updating of customers uses Customer#isSameCustomer(Customer) for equality so as to ensure that the customer being
 * added or updated is unique in terms of identity in the UniqueCustomerList. However, the removal of a customer uses
 * Customer#equals(Object) so as to ensure that the customer with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Customer#isSameCustomer(Customer)
 */
public class UniqueCustomerList implements Iterable<Customer> {

    private final ObservableList<Customer> internalList = FXCollections.observableArrayList();
    private final ObservableList<Customer> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent customer as the given argument.
     */
    public boolean contains(Customer toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCustomer);
    }

    /**
     * Adds a customer to the list.
     * The customer must not already exist in the list.
     */
    public void add(Customer toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCustomerException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in the list.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer in the list.
     */
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CustomerNotFoundException();
        }

        if (!target.isSameCustomer(editedCustomer) && contains(editedCustomer)) {
            throw new DuplicateCustomerException();
        }

        internalList.set(index, editedCustomer);
    }

    /**
     * Removes the equivalent customer from the list.
     * The customer must exist in the list.
     */
    public void remove(Customer toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CustomerNotFoundException();
        }
    }

    public void setCustomers(UniqueCustomerList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code customers}.
     * {@code customers} must not contain duplicate customers.
     */
    public void setCustomers(List<Customer> customers) {
        requireAllNonNull(customers);
        if (!areCustomersUnique(customers)) {
            throw new DuplicateCustomerException();
        }

        internalList.setAll(customers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Customer> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Customer> iterator() {
        return internalList.iterator();
    }

    /**
     * Checks if the commission list contains the same commissions as the oher list of commissions.
     */
    public boolean isSameUniqueCustomerList(UniqueCustomerList other) {
        if (internalList.size() != other.internalList.size()) {
            return false;
        }
        return internalList.stream().allMatch(
                customer -> other.internalList.stream().anyMatch(customer::isSameCustomer));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCustomerList // instanceof handles nulls
                        && internalList.equals(((UniqueCustomerList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code customers} contains only unique customers.
     */
    private boolean areCustomersUnique(List<Customer> customers) {
        for (int i = 0; i < customers.size() - 1; i++) {
            for (int j = i + 1; j < customers.size(); j++) {
                if (customers.get(i).isSameCustomer(customers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
