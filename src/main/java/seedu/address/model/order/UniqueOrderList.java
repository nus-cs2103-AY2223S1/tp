package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.UniqueId;
import seedu.address.model.order.exceptions.DuplicateOrderException;
import seedu.address.model.order.exceptions.OrderNotFoundException;

/**
 * A list of orders that enforces uniqueness between its elements and does not allow nulls.
 * An order is considered unique by comparing using {@code Order#isSameOrder(Order)}. As such, adding and updating of
 * orders uses Order#isSameOrder(Order) for equality so as to ensure that the order being added or updated is
 * unique in terms of identity in the UniqueOrderList. However, the removal of an order uses Order#equals(Object) so
 * as to ensure that the order with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueOrderList implements Iterable<Order> {

    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Order as the given argument.
     */
    public boolean contains(Order toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an Order to the list.
     * The Order must not already exist in the list.
     */
    public void add(Order toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateOrderException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the list.
     * The Order identity of {@code editedOrder} must not be the same as another existing Order in the list.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrderNotFoundException();
        }

        if (!target.equals(editedOrder) && contains(editedOrder)) {
            throw new DuplicateOrderException();
        }

        internalList.set(index, editedOrder);
    }

    /**
     * Removes the equivalent Order from the list.
     * The Order must exist in the list.
     */
    public void remove(Order toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrderNotFoundException();
        }
    }

    public void setOrders(UniqueOrderList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Orders}.
     * {@code Orders} must not contain duplicate Orders.
     */
    public void setOrders(List<Order> orders) {
        requireAllNonNull(orders);
        if (!ordersAreUnique(orders)) {
            throw new DuplicateOrderException();
        }

        internalList.setAll(orders);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Order> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Gets the list of orders from list of ids.
     */
    public List<Order> getOrdersFromId(List<UniqueId> ids) {
        return internalList.stream()
                .filter(order -> ids.stream()
                        .anyMatch(order::hasId))
                .collect(Collectors.toList());
    }

    /**
     * Sorts the list using the specified comparator.
     * @param comparator The specified comparator.
     */
    public void sort(Comparator<Order> comparator) {
        internalList.sort(comparator);
    }

    @Override
    public Iterator<Order> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueOrderList // instanceof handles nulls
                && internalList.equals(((UniqueOrderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true iff {@code orders} contains only unique Orders.
     */
    private boolean ordersAreUnique(List<Order> orders) {
        for (int i = 0; i < orders.size() - 1; i++) {
            for (int j = i + 1; j < orders.size(); j++) {
                if (orders.get(i).equals(orders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
