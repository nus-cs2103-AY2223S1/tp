package seedu.pennywise.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.pennywise.model.entry.Entry;

/**
 * A UI component that displays information of a {@code entry}.
 */
public class EntryCard extends UiPart<Region> {

    private static final String FXML = "EntryListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PennyWise level 4</a>
     */

    public final Entry entry;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label amount;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code EntryCodeCode} with the given {@code Entry} and index to display.
     */
    public EntryCard(Entry entry, int displayedIndex) {
        super(FXML);
        this.entry = entry;
        id.setText(displayedIndex + ". ");
        description.setText(entry.getDescription().fullDescription);
        amount.setText(entry.getAmount().toFormattedString());
        date.setText(entry.getDate().toString());
        tags.getChildren().add(new Label(entry.getTag().getTagName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EntryCard)) {
            return false;
        }

        // state check
        EntryCard card = (EntryCard) other;
        return id.getText().equals(card.id.getText())
                && entry.equals(card.entry);
    }
}
