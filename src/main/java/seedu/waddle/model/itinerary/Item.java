package seedu.waddle.model.itinerary;

import static java.util.Objects.requireNonNull;

/**
 * Represents an item in the itinerary.
 */
public class Item {
    private String description;
    // private Priority priority;
    // private Category category;

    /**
     * Constructor for an item.
     * @param description description of the item
     */
    public Item(String description) {
        requireNonNull(description);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns true if both items have the same description.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getDescription().equals(getDescription());
    }


}
