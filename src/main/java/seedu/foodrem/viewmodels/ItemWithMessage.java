package seedu.foodrem.viewmodels;

import seedu.foodrem.model.item.Item;

public class ItemWithMessage {
    private final Item item;
    private final String message;

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
