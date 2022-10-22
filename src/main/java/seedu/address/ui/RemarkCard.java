package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.remark.Remark;

/**
 * A UI component that displays information of a {@code Remark}.
 */
public class RemarkCard extends UiPart<Region> {

    private static final String FXML = "RemarkListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on JeeqTracker level 4</a>
     */

    public final Remark remark;

    @FXML
    private HBox remarkCardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label address;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code RemarkCode} with the given {@code Remark} and index to display
     */
    public RemarkCard(Remark remark, int displayedIndex) {
        super(FXML);
        this.remark = remark;
        id.setText(displayedIndex + ". ");
        name.setText(remark.getName().toString());
        address.setText("Address: " + remark.getAddress().toString());
        remark.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof RemarkCard)) {
            return false;
        }

        // state check
        RemarkCard card = (RemarkCard) other;
        return id.getText().equals(card.id.getText())
                && remark.equals(card.remark);
    }
}
