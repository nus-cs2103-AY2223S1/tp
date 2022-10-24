package seedu.foodrem.views;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.model.item.Item;
import seedu.foodrem.ui.ResultDisplay;
import seedu.foodrem.viewmodels.ItemWithMessage;

public class UiView {
    private final ResultDisplay display;

    public UiView(ResultDisplay display) {
        this.display = display;
    }

    // TODO: Use a less hacky method
    public void viewFrom(Object object) {
        requireNonNull(object);
        display.clear();
        if (object instanceof String) {
            display.place(StringView.from((String) object));
            return;
        }
        if (object instanceof Item) {
            display.place(ItemView.from((Item) object));
            return;
        }
        if (object instanceof ItemWithMessage) {
            display.place(ItemWithMessageView.from((ItemWithMessage) object));
            return;
        }

        // Code should not reach here unless there is programmer error
        throw new UnsupportedOperationException();
    }
}
