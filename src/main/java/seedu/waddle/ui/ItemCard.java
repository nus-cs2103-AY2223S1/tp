package seedu.waddle.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.text.Text;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class ItemCard extends UiPart<Region> {
    private static final String FXML = "ItemListCard.fxml";
    public final Item item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    // Priority and Category have not yet been implemented
    @FXML
    private Label priority;
    @FXML
    private Label duration;
    @FXML
    private Label time;
    @FXML
    private Label cost;
    // @FXML
    // private Label category;

    /**
     * Creates a {@code ItemCode} with the given {@code Item} and index to display.
     */
    public ItemCard(Item item, int dayNumber, int displayedIndex) {
        super(FXML);
        this.item = item;
        if (dayNumber > 0) {
            this.id.setText(dayNumber + "." + displayedIndex + " ");
        } else {
            this.id.setText(displayedIndex + ". ");
        }
        this.description.setText(item.getDescriptionString(Text.indentNone));
        this.priority.setText(item.getPriorityString(Text.indentNone));
        this.duration.setText(item.getDurationString(Text.indentNone));
        this.time.setText(item.getTimeString(Text.indentNone));
        this.cost.setText(item.getCostString(Text.indentNone));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItemCard)) {
            return false;
        }

        // state check
        ItemCard card = (ItemCard) other;
        return id.getText().equals(card.id.getText())
                && item.equals(card.item);
    }

}
