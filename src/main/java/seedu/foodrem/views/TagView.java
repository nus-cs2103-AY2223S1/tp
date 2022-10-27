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
        final Label label = new Label(tag.getName());
        label.getStyleClass().add("item-detail-tag");
        return label;
    }
}
