package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.staff.Staff;

/**
 * Unmodifiable view of a staff list
 */
public interface ReadOnlyStaffList {

    /**
     * Returns an unmodifiable view of the staffs list.
     * This list will not contain any duplicate staffs.
     */
    ObservableList<Staff> getStaffList();

}
