package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
        Label label = new Label(message);
        label.setWrapText(true);
        // TODO: Use css
        label.setMaxWidth(400);
        return new VBox(label);
    }
}
