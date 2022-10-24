package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * @author Richard Dominick
 */
public class ItemWithMessageView {
    private static final double SPACING_UNIT = 8;

    public static Node from(ItemWithMessage itemWithMessage) {
        final VBox container = new VBox();
        container.setSpacing(SPACING_UNIT);

        final Label messageView = new Label(itemWithMessage.getMessage());
        final Separator separator = new Separator();
        separator.getStyleClass().add("lined-separator");
        final Node itemView = ItemView.from(itemWithMessage.getItem());

        container.getChildren().addAll(messageView, separator, itemView);
        return container;
    }
}
