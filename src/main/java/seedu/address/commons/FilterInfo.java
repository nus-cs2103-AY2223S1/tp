package seedu.address.commons;

/**
 * Class representing the filter information to be displayed with the task list UI.
 */
public class FilterInfo {
    private static final String FILTER_INFO_PREFIX = "Filtered by: ";

    private String info;

    /**
     * @param info what the task list is filtered by
     */
    public FilterInfo(String info) {
        this.info = info;
    }

    /**
     * Creates a {@code FilterInfo} for an unfiltered task list.
     */
    public FilterInfo() {
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
        return FILTER_INFO_PREFIX + info;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterInfo)) {
            return false;
        }

        // state check
        FilterInfo otherFilter = (FilterInfo) other;
        return info.equals(otherFilter.info);
    }
}
