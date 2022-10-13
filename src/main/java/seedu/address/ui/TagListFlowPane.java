package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tag.Tag;

/**
 * Flow pane containing the list of tags.
 */

public class TagListFlowPane extends UiPart<Region> {
    private static final String FXML = "TagListFlowPane.fxml";
    private final Logger logger = LogsCenter.getLogger(TagListFlowPane.class);

    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TagListFlowPane} with the given {@code ObservableList}.
     */
    public TagListFlowPane(ObservableList<Tag> tagList) {
        super(FXML);
        tagList.stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
