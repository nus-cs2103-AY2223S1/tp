package seedu.address.model.commission;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.commission.exceptions.CommissionNotFoundException;
import seedu.address.model.commission.exceptions.DuplicateCommissionException;

/**
 * A list of commissions that enforces uniqueness between its elements and does not allow nulls.
 * A commission is considered unique by comparing using {@code Commission#isSameCommission(Commission)}. As such, adding
 * and updating of commissions uses Commission#isSameCommission(Commission) for equality so as to ensure that the
 * commission being added or updated is unique in terms of identity in the UniqueCommissionList. However, the removal of
 * a commission uses Commission#equals(Object) so as to ensure that the commission with exactly the same fields will be
 * removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Commission#isSameCommission(Commission)
 */
public class UniqueCommissionList implements Iterable<Commission> {

    private final ObservableList<Commission> internalList = FXCollections.observableArrayList();
    private final ObservableList<Commission> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent commission as the given argument.
     */
    public boolean contains(Commission toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCommission);
    }

    /**
     * Adds a commission to the list.
     * The commission must not already exist in the list.
     */
    public void add(Commission toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCommissionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns the size of the commission list.
     */
    public int getSize() {
        return internalList.size();
    }

    /**
     * Returns the number of active commissions.
     */
    public long getActiveSize() {
        return internalList.stream()
                .filter(commission -> !commission.getCompletionStatus().isCompleted)
                .count();
    }

    /**
     * Returns the last Deadline.
     */
    public LocalDate getLastDate() {
        return internalList.stream()
                .map(commission -> commission.getDeadline().deadline)
                .reduce(null, (result, date) -> {
                    if (result == null) {
                        return date;
                    }
                    if (result.compareTo(date) < 0) {
                        return date;
                    }
                    return result;
                });
    }

    /**
     * Replaces the commission {@code target} in the list with {@code editedCommission}.
     * {@code target} must exist in the list.
     * The commission identity of {@code editedCommission} must not be the same as another existing commission
     * in the list.
     */
    public void setCommission(Commission target, Commission editedCommission) {
        requireAllNonNull(target, editedCommission);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CommissionNotFoundException();
        }

        if (!target.isSameCommission(editedCommission) && contains(editedCommission)) {
            throw new DuplicateCommissionException();
        }

        internalList.set(index, editedCommission);
    }

    /**
     * Removes the equivalent commission from the list.
     * The commission must exist in the list.
     */
    public void remove(Commission toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CommissionNotFoundException();
        }
    }

    public void setCommissions(UniqueCommissionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code commissions}.
     * {@code commissions} must not contain duplicate commissions.
     */
    public void setCommissions(List<Commission> commissions) {
        requireAllNonNull(commissions);
        if (!areCommissionsUnique(commissions)) {
            throw new DuplicateCommissionException();
        }

        internalList.setAll(commissions);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Commission> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Commission> iterator() {
        return internalList.iterator();
    }

    /** Returns size of the commission list. */
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCommissionList // instanceof handles nulls
                && internalList.equals(((UniqueCommissionList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code commissions} contains only unique commissions.
     */
    private boolean areCommissionsUnique(List<Commission> commissions) {
        for (int i = 0; i < commissions.size() - 1; i++) {
            for (int j = i + 1; j < commissions.size(); j++) {
                if (commissions.get(i).isSameCommission(commissions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

