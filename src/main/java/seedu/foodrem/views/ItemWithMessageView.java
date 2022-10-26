package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * A view of an {@code ItemWithMessage}. This can be displayed.
 * @author Richard Dominick
 */
public class ItemWithMessageView {
    private static final double SPACING_UNIT = 8;

    /**
     * Creates a new detailed view of the given item as well as
     * message contained in the {@code ItemWithMessage} view model.
     * @param itemWithMessage the item to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(ItemWithMessage itemWithMessage) {
        final VBox container = new VBox();
        container.setSpacing(SPACING_UNIT);

        final Label messageView = new Label(itemWithMessage.getMessage());
        messageView.setWrapText(true);
        final Separator separator = new Separator();
        separator.getStyleClass().add("lined-separator");
        final Node itemView = ItemView.from(itemWithMessage.getItem());

        container.getChildren().addAll(messageView, separator, itemView);
        return container;
    }
}
