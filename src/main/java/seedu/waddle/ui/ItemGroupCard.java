package seedu.waddle.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.waddle.model.item.Item;

/**
 * An UI component that displays information of a {@code Itinerary}.
 */
public class ItemGroupCard extends UiPart<Region> {

    private static final String FXML = "ItemGroupListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ObservableList<Item> itemGroup;

    @FXML
    private Label dayNumber;
    @FXML
    private StackPane itemListPanelPlaceholder;

    /**
     * Creates a {@code ItineraryCode} with the given {@code Itinerary} and index to display.
     */
    public ItemGroupCard(ObservableList<Item> itemGroup, int dayNumber) {
        super(FXML);
        this.itemGroup = itemGroup;
        if (dayNumber == 0) {
            this.dayNumber.setText("Wishlist");
        } else {
            this.dayNumber.setText("Day " + dayNumber);
        }
        this.itemListPanelPlaceholder.getChildren().add(new ItemListPanel(itemGroup, dayNumber).getRoot());
        this.itemListPanelPlaceholder.setMinHeight(UiSizes.ITEM_LIST_MIN_HEIGHT);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItemGroupCard)) {
            return false;
        }

        // state check
        ItemGroupCard card = (ItemGroupCard) other;
        return dayNumber.getText().equals(card.dayNumber.getText())
                && itemGroup.equals(card.itemGroup);
    }
}
