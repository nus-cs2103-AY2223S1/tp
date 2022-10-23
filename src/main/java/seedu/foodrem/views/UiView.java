package seedu.foodrem.views;

import javafx.scene.control.TextArea;
import seedu.foodrem.ui.ResultDisplay;

import static java.util.Objects.requireNonNull;

public class UiView {
    private final ResultDisplay display;

    public UiView(ResultDisplay display) {
        this.display = display;
    }

    public void viewFrom(String message) {
        requireNonNull(message);
        display.clear();
        final TextArea textArea = new TextArea(message);
        textArea.setEditable(false);
        display.place(textArea);
    }
}
