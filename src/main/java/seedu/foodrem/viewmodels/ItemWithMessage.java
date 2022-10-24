package seedu.foodrem.viewmodels;

import seedu.foodrem.model.item.Item;

/**
 * A view model for generating a view with an item as well as a message.
 * @author Richard Dominick
 */
public class ItemWithMessage {
    private final Item item;
    private final String message;

    /**
     * Creates a view model containing the message and the item to display.
     * @param item the item to display.
     * @param message the message to display.
     */
    public ItemWithMessage(Item item, String message) {
        this.item = item;
        this.message = message;
    }

    public Item getItem() {
        return item;
    }

    public String getMessage() {
        return message;
    }
}
