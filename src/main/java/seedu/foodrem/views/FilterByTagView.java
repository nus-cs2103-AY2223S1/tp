package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import seedu.foodrem.viewmodels.FilterByTag;

/**
 * A view of a {@code FilterByTag}. This can be displayed.
 * @author Richard Dominick
 */
public class FilterByTagView {
    private static final double SPACING_UNIT = 8;

    /**
     * Creates a new detailed view of the given tags as well as
     * message contained in the {@code FilterByTag} view model.
     * @param filterByTag the tags to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(FilterByTag filterByTag) {
        final VBox container = new VBox();
        container.setSpacing(SPACING_UNIT);

        final Label primaryMessage = new Label(filterByTag.getPrimaryMessage());
        primaryMessage.setWrapText(true);
        final Separator separator = new Separator();
        separator.getStyleClass().add("lined-separator");
        final Node itemView = TagsView.from(filterByTag.getTag());
        final Label secondaryMessage = new Label(filterByTag.getSecondaryMessage());
        secondaryMessage.setWrapText(true);

        container.getChildren().addAll(primaryMessage, separator, itemView, secondaryMessage);
        return container;
    }
}
