package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import seedu.foodrem.viewmodels.TagsWithMessage;

/**
 * A view of an {@code TagsWithMessage}. This can be displayed.
 * @author Richard Dominick
 */
public class TagsWithMessageView {
    private static final double SPACING_UNIT = 8;

    /**
     * Creates a new detailed view of the given tags as well as
     * message contained in the {@code TagsWithMessage} view model.
     * @param tagsWithMessage the tags to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(TagsWithMessage tagsWithMessage) {
        final VBox container = new VBox();
        container.setSpacing(SPACING_UNIT);

        final Label messageView = new Label(tagsWithMessage.getMessage());
        messageView.setWrapText(true);
        final Separator separator = new Separator();
        separator.getStyleClass().add("lined-separator");
        final Node itemView = TagsView.from(tagsWithMessage.getTags());

        container.getChildren().addAll(messageView, separator, itemView);
        return container;
    }
}
