package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.UniqueStaffList;

/**
 * Wraps all data at the staff list level
 * Duplicates are not allowed (by .isSameStaff comparison)
 */
public class StaffList implements ReadOnlyStaffList {

    private final UniqueStaffList staffs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        staffs = new UniqueStaffList();
    }

    public StaffList() {}

    //// list overwrite operations

    /**
     * Replaces the contents of the staff list with {@code staffs}.
     * {@code staffs} must not contain duplicate staffs.
     */
    public void setStaffs(List<Staff> staffs) {
        this.staffs.setStaffs(staffs);
    }

    /**
     * Resets the existing data of this {@code StaffList} with {@code newData}.
     */
    public void resetData(ReadOnlyStaffList newData) {
        requireNonNull(newData);

        setStaffs(newData.getStaffList());
    }

    //// staff-level operations

    /**
     * Returns true if a staff with the same identity as {@code staff} exists in the staff list.
     */
    public boolean hasStaff(Staff staff) {
        requireNonNull(staff);
        return staffs.contains(staff);
    }

    /**
     * Adds a staff to the staff list.
     * The staff must not already exist in the staff list.
     */
    public void addStaff(Staff s) {
        staffs.add(s);
    }

    /**
     * Replaces the given staff {@code target} in the list with {@code editedStaff}.
     * {@code target} must exist in the staff list.
     * The staff identity of {@code editedStaff} must not be the same as another
     * existing staff in the staff list.
     */
    public void setStaff(Staff target, Staff editedStaff) {
        requireNonNull(editedStaff);

        staffs.setStaff(target, editedStaff);
    }

    /**
     * Removes {@code key} from this {@code StaffList}.
     * {@code key} must exist in the staff list.
     */
    public void removeStaff(Staff key) {
        staffs.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return staffs.asUnmodifiableObservableList().size() + " staffs";
        // TODO: refine later
    }

    @Override
    public ObservableList<Staff> getStaffList() {
        return staffs.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffList // instanceof handles nulls
                && staffs.equals(((StaffList) other).staffs));
    }

    @Override
    public int hashCode() {
        return staffs.hashCode();
    }





}
