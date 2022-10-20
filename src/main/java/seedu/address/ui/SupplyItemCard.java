package seedu.address.ui;
import static seedu.address.commons.util.IntegerUtil.getStringFromInt;
import static seedu.address.commons.util.StockUtil.determineStockHealthColor;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.item.SupplyItem;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class SupplyItemCard extends UiPart<Region> {
    /**
     * These colors are made public for possible accessibility in other Ui components.
     */
    private static final String FXML = "SupplyItemCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final SupplyItem supplyItem;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label supplierName;
    @FXML
    private Label currentStock;
    @FXML
    private Label price;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code SupplyItemCard} with the given {@code SupplyItem} and index to display.
     */
    public SupplyItemCard(SupplyItem supplyItem, int displayedIndex) {
        super(FXML);
        this.supplyItem = supplyItem;
        cardPane.setStyle(String.format("-fx-border-color:%s ; -fx-border-width: 0 6 0 0;",
                determineStockHealthColor(supplyItem.getCurrentStock(), supplyItem.getMinStock())));
        id.setText(displayedIndex + ". ");
        name.setText(supplyItem.getName());
        supplierName.setText(supplyItem.getSupplier().getName().fullName);
        currentStock.setText(getStringFromInt(supplyItem.getCurrentStock()));
        price.setText(supplyItem.getPrice().value);
        supplyItem.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SupplyItemCard)) {
            return false;
        }

        // state check
        SupplyItemCard card = (SupplyItemCard) other;
        return id.getText().equals(card.id.getText())
                && supplyItem.equals(card.supplyItem);
    }
}
