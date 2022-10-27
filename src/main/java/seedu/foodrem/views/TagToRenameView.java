package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.foodrem.viewmodels.TagToRename;

/**
 * A view of an {@code TagToRename}. This can be displayed.
 * @author Richard Dominick
 */
public class TagToRenameView {
    private static final double SPACING_UNIT = 8;

    /**
     * Creates a new detailed view of the tags to be renamed, the renamed tag, all tags
     * and the message contained in the {@code TagToRename} view model.
     * @param tagToRename the information of tag to be renamed and message.
     * @return the node to be displayed in the UI.
     */
    public static Node from(TagToRename tagToRename) {
        final VBox container = new VBox();
        container.setSpacing(SPACING_UNIT);

        final Label messageView = new Label(tagToRename.getMessage());
        messageView.setWrapText(true);
        final Separator separator = new Separator();
        separator.getStyleClass().add("lined-separator");

        final HBox originalTag = new HBox(new Label("Original Tag:"),
                TagsView.from(tagToRename.getOriginalTag()));
        final HBox renamedTag = new HBox(new Label("Renamed Tag:"),
                TagsView.from(tagToRename.getRenamedTag()));

        originalTag.setSpacing(SPACING_UNIT);
        renamedTag.setSpacing(SPACING_UNIT);

        container.getChildren().addAll(messageView, separator, originalTag, renamedTag);
        return container;
    }
}
