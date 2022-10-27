package seedu.foodrem.views;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.foodrem.model.tag.Tag;

/**
 * A view of a list of {@code Tags}. This can be displayed.
 * @author Richard Dominick
 */
public class TagsView {

    private static final double SPACING_UNIT = 8;
    private static final String EMPTY_TAGS = "-";

    private TagsView() {} // Prevents instantiation

    /**
     * Creates a new detailed view of the given tags from a list.
     * @param itemTags the tags to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(List<Tag> itemTags) {
        final List<Node> tagsList = new ArrayList<>();
        itemTags.stream().sorted(Comparator.comparing(Tag::getName))
                .forEach(tag -> tagsList.add(buildTagNodeFrom(tag.getName())));
        if (tagsList.isEmpty()) {
            tagsList.add(new Label(EMPTY_TAGS));
        }

        HBox tagsHBox = new HBox(tagsList.toArray(Node[]::new));
        tagsHBox.setSpacing(SPACING_UNIT);
        return tagsHBox;
    }

    /**
     * Creates a new detailed view of the given tags from a set.
     * @param itemTags the tags to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Set<Tag> itemTags) {
        return from(new ArrayList<>(itemTags));
    }

    /**
     * Creates a new detailed view of a given tag.
     * @param itemTag the tag to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Tag itemTag) {
        return from(List.of(itemTag));
    }

    private static Node buildTagNodeFrom(String tagName) {
        final Label label = new Label(tagName);
        label.getStyleClass().add("item-detail-tag");
        return label;
    }
}
