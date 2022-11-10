package seedu.address.commons;

/**
 * Class representing the sort information to be displayed with the task list UI.
 */
public class SortInfo {
    private static final String SORT_INFO_PREFIX = "Sorted by: ";

    private String info;

    /**
     * @param info what the task list is sorted by
     */
    public SortInfo(String info) {
        this.info = info;
    }

    /**
     * Creates a {@code SortInfo} for an unsorted task list.
     */
    public SortInfo() {
        this.info = "none";
    }

    /**
     * Returns a String of the {@code info} field.
     */
    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return SORT_INFO_PREFIX + info;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortInfo)) {
            return false;
        }

        // state check
        SortInfo otherSort = (SortInfo) other;
        return info.equals(otherSort.info);
    }
}
