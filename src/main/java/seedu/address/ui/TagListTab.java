package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import seedu.address.model.tag.Tag;

/**
 * Tab containing the list of tags.
 */
public class TagListTab extends Tab {
    private static final String TAB_NAME = "labels";

    /**
     * Creates a {@code TagListTab} with the given {@code ObservableList}.
     */
    public TagListTab(ObservableList<Tag> tagList) {
        super(TAB_NAME, new TagListPanel(tagList).getRoot());
    }
}
