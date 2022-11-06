package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.foodrem.model.tag.Tag;

/**
 * A view of a {@code Tag}. This can be displayed.
 * @author Richard Dominick
 */
public class TagView {
    private static final String[] COLORS = new String[] {
        "f3d3b5", "abdee6", "ffccb6", "ff9aa2", "c7ceea", "a5e3b3", "e2f0cb", "c5d7c0", "f3d1dc", "e6ede5",
        "c8b4ba", "98afba", "70ae98"
    };

    private TagView() {} // Prevents instantiation

    /**
     * Creates a new view of the given string.
     * @param tag the tag to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Tag tag) {
        return from(tag, false);
    }

    /**
     * Creates a new view of the given string.
     * @param tag the tag to be displayed.
     * @param isSmall whether the tag is small.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Tag tag, boolean isSmall) {
        final Label label = new Label(tag.getName());
        label.getStyleClass().add(isSmall ? "item-listview-tag" : "item-detail-tag");
        label.setStyle(String.format("-fx-background-color: #%s;", getColor(tag)));
        return label;
    }

    private static String getColor(Tag tag) {
        final int index = Math.abs(tag.hashCode() % COLORS.length);
        try {
            return COLORS[index];
        } catch (IndexOutOfBoundsException err) {
            // This should never occur (Defensive Coding)
            return COLORS[0];
        }
    }
}
