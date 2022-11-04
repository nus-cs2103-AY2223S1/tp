package seedu.hrpro.model.staff;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.model.staff.exceptions.DuplicateStaffException;
import seedu.hrpro.model.staff.exceptions.StaffNotFoundException;

/**
 * A list of staffs that enforces uniqueness between its elements and does not allow nulls.
 * A staff is considered unique by comparing using {@code Staff#isSameStaff(Staff)}. As such,
 * adding and updating of staffs uses Staff#isSameStaff(Staff) for equality to ensure that the
 * staff being added or updated is unique in terms of identity in the UniqueStaffList. However,
 * the removal of a staff uses Staff#equals(Object) to ensure that the staff with exactly the
 * same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Staff#isSameStaff(Staff)
 */
public class UniqueStaffList implements Iterable<Staff> {
    private final ObservableList<Staff> internalList = FXCollections.observableArrayList();
    private final ObservableList<Staff> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent staff as the given argument.
     */
    public boolean contains(Staff toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStaff);
    }

    /**
     * Adds a staff to the list.
     * The staff must not already exist in the list.
     */
    public void add(Staff toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStaffException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the staff {@code target} in the list with {@code editedStaff}.
     * {@code target} must exist in the list.
     * The staff identity of {@code editedStaff} must not be the same as another existing staff in the list.
     */
    public void setStaff(Staff target, Staff editedStaff) {
        requireAllNonNull(target, editedStaff);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StaffNotFoundException();
        }

        if (!target.isSameStaff(editedStaff) && contains(editedStaff)) {
            throw new DuplicateStaffException();
        }

        internalList.set(index, editedStaff);
    }

    /**
     * Removes the equivalent staff from the list.
     * The staff must exist in the list.
     */
    public void remove(Staff toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StaffNotFoundException();
        }
    }

    public void setStaffs(UniqueStaffList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code staffs}.
     * {@code staffs} must not contain duplicate staffs.
     */
    public void setStaffs(List<Staff> staffs) {
        requireAllNonNull(staffs);
        if (!staffsAreUnique(staffs)) {
            throw new DuplicateStaffException();
        }

        internalList.setAll(staffs);
    }

    /**
     * Returns the size of the {@code UniqueStaffList}.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns the {@code Staff} at {@code Index} of the {@code UniqueStaffList}.
     */
    public Staff getStaff(Index index) {
        return internalList.get(index.getZeroBased());
    }



    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Staff> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    @Override
    public Iterator<Staff> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStaffList // instanceof handles nulls
                && internalList.equals(((UniqueStaffList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code staffs} contains only unique staffs.
     */
    private boolean staffsAreUnique(List<Staff> staffs) {
        for (int i = 0; i < staffs.size() - 1; i++) {
            for (int j = i + 1; j < staffs.size(); j++) {
                if (staffs.get(i).isSameStaff(staffs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
