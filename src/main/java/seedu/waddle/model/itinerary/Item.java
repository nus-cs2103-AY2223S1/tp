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
}
