package seedu.boba.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.boba.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.boba.model.customer.exceptions.DuplicatePersonException;
import seedu.boba.model.customer.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A customer is considered unique by comparing using {@code Customer#isSamePerson(Customer)}.
 * As such, adding and updating ofpersons uses Customer#isSamePerson(Customer) for equality so as to ensure
 * that the customer being added or updated is unique in terms of identity in the UniqueCustomerList.
 * However, the removal of a customer uses Customer#equals(Object) so as to ensure that the customer with
 * exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Customer#isSamePerson(Customer)
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
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Returns the index of the Customer with the same phone number.
     *
     * @param toCheck Phone number to check against the list
     * @return index of the Customer with the same phone number in the list
     * @throws PersonNotFoundException if no Customer with corresponding phone number found
     */
    public int findNum(Phone toCheck) throws PersonNotFoundException {
        requireNonNull(toCheck);
        int index = internalList.stream().map(Customer::getPhone).collect(Collectors.toList()).indexOf(toCheck);
        if (index == -1) {
            throw new PersonNotFoundException();
        }
        return index;
    }

    /**
     * Returns the index of the Customer with the same email.
     *
     * @param toCheck Email to check against the list
     * @return index of the Customer with the same email in the list
     * @throws PersonNotFoundException if no Customer with corresponding email found
     */
    public int findEmail(Email toCheck) throws PersonNotFoundException {
        requireNonNull(toCheck);
        int index = internalList.stream().map(Customer::getEmail).collect(Collectors.toList()).indexOf(toCheck);
        if (index == -1) {
            throw new PersonNotFoundException();
        }
        return index;
    }

    /**
     * Returns the current Reward points of a Customer
     *
     * @param phone Phone number of the Customer of interest
     * @return the current Reward points of a Customer
     */
    public Reward getCurrentReward(Phone phone) {
        requireNonNull(phone);
        Optional<Reward> currentReward = internalList.stream().filter(person -> person.getPhone().equals(phone))
                .map(Customer::getReward).findFirst();
        return currentReward.get();
    }

    /**
     * Returns the current Reward points of a Customer
     *
     * @param email Email of the Customer of interest
     * @return the current Reward points of a Customer
     */
    public Reward getCurrentReward(Email email) {
        requireNonNull(email);
        Optional<Reward> currentReward = internalList.stream().filter(person -> person.getEmail().equals(email))
                .map(Customer::getReward).findFirst();
        return currentReward.get();
    }

    /**
     * Adds a customer to the list.
     * The customer must not already exist in the list.
     */
    public void add(Customer toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in the list.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer in the list.
     */
    public void setPerson(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedCustomer) && contains(editedCustomer)) {
            throw new DuplicatePersonException();
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
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniqueCustomerList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code customers}.
     * {@code customers} must not contain duplicate customers.
     */
    public void setPersons(List<Customer> customers) {
        requireAllNonNull(customers);
        if (!personsAreUnique(customers)) {
            throw new DuplicatePersonException();
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCustomerList // instanceof handles nulls
                && internalList.equals(((UniqueCustomerList) other).internalList));
    }

    /**
     * Compares if two Lists are strictly equals
     * which is true only when each person in the lists are strictly equals
     * @param other
     * @return
     */
    public boolean strictlyEquals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UniqueCustomerList)) {
            return false;
        }
        UniqueCustomerList otherList = (UniqueCustomerList) other;
        if (this.internalList.size() != otherList.internalList.size()) {
            return false;
        }
        for (int i = 0; i < this.internalList.size(); i++) {
            if (!internalList.get(i).strictlyEquals(otherList.internalList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code customers} contains only unique customers.
     */
    private boolean personsAreUnique(List<Customer> customers) {
        for (int i = 0; i < customers.size() - 1; i++) {
            for (int j = i + 1; j < customers.size(); j++) {
                if (customers.get(i).isSamePerson(customers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
