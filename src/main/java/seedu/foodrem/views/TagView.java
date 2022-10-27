package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.foodrem.model.tag.Tag;

/**
 * A view of a {@code Tag}. This can be displayed.
 * @author Richard Dominick
 */
public class TagView {
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
        return label;
    }
}
