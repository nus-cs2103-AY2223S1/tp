package seedu.address.model.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.buyer.exceptions.DuplicateBuyerException;
import seedu.address.model.buyer.exceptions.BuyerNotFoundException;

/**
 * A list of buyers that enforces uniqueness between its elements and does not allow nulls.
 * A buyer is considered unique by comparing using {@code Buyer#isSameBuyer(Buyer)}. As such, adding and updating of
 * buyers uses Buyer#isSameBuyer(Buyer) for equality so as to ensure that the buyer being added or updated is
 * unique in terms of identity in the UniqueBuyerList. However, the removal of a buyer uses Buyer#equals(Object) so
 * as to ensure that the buyer with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Buyer#isSameBuyer(Buyer)
 */
public class UniqueBuyerList implements Iterable<Buyer> {

    private final ObservableList<Buyer> internalList = FXCollections.observableArrayList();
    private final ObservableList<Buyer> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent buyer as the given argument.
     */
    public boolean contains(Buyer toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBuyer);
    }

    /**
     * Adds a buyer to the list.
     * The buyer must not already exist in the list.
     */
    public void add(Buyer toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBuyerException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the buyer {@code target} in the list with {@code editedBuyer}.
     * {@code target} must exist in the list.
     * The buyer identity of {@code editedBuyer} must not be the same as another existing buyer in the list.
     */
    public void setBuyer(Buyer target, Buyer editedBuyer) {
        requireAllNonNull(target, editedBuyer);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BuyerNotFoundException();
        }

        if (!target.isSameBuyer(editedBuyer) && contains(editedBuyer)) {
            throw new DuplicateBuyerException();
        }

        internalList.set(index, editedBuyer);
    }

    /**
     * Removes the equivalent buyer from the list.
     * The buyer must exist in the list.
     */
    public void remove(Buyer toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BuyerNotFoundException();
        }
    }

    public void setBuyers(UniqueBuyerList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code buyers}.
     * {@code buyers} must not contain duplicate buyers.
     */
    public void setBuyers(List<Buyer> buyers) {
        requireAllNonNull(buyers);
        if (!buyersAreUnique(buyers)) {
            throw new DuplicateBuyerException();
        }

        internalList.setAll(buyers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Buyer> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Buyer> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBuyerList // instanceof handles nulls
                        && internalList.equals(((UniqueBuyerList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code buyers} contains only unique buyers.
     */
    private boolean buyersAreUnique(List<Buyer> buyers) {
        for (int i = 0; i < buyers.size() - 1; i++) {
            for (int j = i + 1; j < buyers.size(); j++) {
                if (buyers.get(i).isSameBuyer(buyers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
