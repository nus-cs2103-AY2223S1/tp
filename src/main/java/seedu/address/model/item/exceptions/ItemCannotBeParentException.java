package seedu.address.model.item.exceptions;

import seedu.address.model.item.DisplayItem;

public class ItemCannotBeParentException extends RuntimeException {
    public ItemCannotBeParentException(DisplayItem item) {
        super(String.format("Cannot be added to %s", item.toString()));
    }
}
