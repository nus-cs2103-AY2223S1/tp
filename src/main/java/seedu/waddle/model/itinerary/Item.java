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

    /*
    public String getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }
    */

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Priority: ")
                .append("; Category: ");
        return builder.toString();
    }
}