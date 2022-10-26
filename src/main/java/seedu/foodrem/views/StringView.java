package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

/**
 * A view of a {@code String}. This can be displayed.
 * @author Richard Dominick
 */
public final class StringView {
    private StringView() {} // Prevents instantiation

    /**
     * Creates a new view of the given string.
     * @param message the string to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(String message) {
        final TextArea textArea = new TextArea(message);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        return textArea;
    }
}
