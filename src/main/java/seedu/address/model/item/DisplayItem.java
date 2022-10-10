package seedu.address.model.item;

/**
 * Represents a unique item in the addressbook, it can be either a accessable
 * team or an entry
 */
public interface DisplayItem {
    /**
     * Returns the entry type of the displayable item to determine which fxml layout
     * card will be used to display this item.
     */
    EntryType getEntryType();

    /**
     * Defines a stronger notions of equality between display items.
     */
    boolean stronglyEqual(DisplayItem o);

    /**
     * Defines a weaker notion of equality between display items.
     */
    boolean weaklyEqual(DisplayItem o);

    /**
     * Make the current item to belong under {@code DisplayItem o}
     */
    void setParent(DisplayItem o);

    /**
     * Returns true if {@code DisplayItem o} is a parent of this item
     */
    boolean isPartOfContext(DisplayItem o);
}
