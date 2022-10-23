package seedu.foodrem.views;

import javafx.scene.control.TextArea;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.ui.ResultDisplay;

import static java.util.Objects.requireNonNull;

public class UiView {
    private final ResultDisplay display;

    public UiView(ResultDisplay display) {
        this.display = display;
    }

    private void viewFrom(String message) {
        requireNonNull(message);
        display.clear();
        final TextArea textArea = new TextArea(message);
        textArea.setEditable(false);
        display.place(textArea);
    }

    private void viewFrom(Item item) {
        requireNonNull(item);
        display.clear();
        // TODO: Implement this
    }

    // TODO: Use a less hacky method
    public void viewFrom(Object object) {
        if (object instanceof String) {
            viewFrom((String) object);
        } else if (object instanceof Item) {
            viewFrom((Item) object);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
