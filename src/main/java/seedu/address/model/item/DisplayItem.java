package seedu.address.model.item;

/**
 * Represents a unique item in the addressbook, it can be either a accessable
 * team or an entry
 */
public interface DisplayItem {
    EntryType getEntryType();

    boolean stronglyEqual(DisplayItem o);

    boolean weaklyEqual(DisplayItem o);

    String getFullPathName();

    void setParent(DisplayItem o);
}
