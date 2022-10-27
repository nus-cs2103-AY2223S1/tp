package seedu.foodrem.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.foodrem.model.tag.Tag;

/**
 * A view of a list of {@code Tags}. This can be displayed.
 * @author Tan Yi Xian
 */
public class TagsView {

    private static final double SPACING_UNIT = 8;
    private static final String EMPTY_TAGS = "-";

    private TagsView() {} // Prevents instantiation

    /**
     * Creates a new detailed view of the given tags from a list.
     * @param tags the tags to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Tag... tags) {
        final List<Node> tagsList = new ArrayList<>();
        Arrays.stream(tags).sorted(Comparator.comparing(Tag::getName))
                .forEach(tag -> tagsList.add(TagView.from(tag)));
        if (tagsList.isEmpty()) {
            tagsList.add(new Label(EMPTY_TAGS));
        }

        final HBox view = new HBox(tagsList.toArray(Node[]::new));
        view.setSpacing(SPACING_UNIT);
        return view;
    }

    /**
     * Creates a new detailed view of the given tags from a set.
     * @param tags the tags to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Collection<Tag> tags) {
        return from(tags.toArray(Tag[]::new));
    }
}
