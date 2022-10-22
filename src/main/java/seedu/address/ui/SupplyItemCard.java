package seedu.address.ui;
import static seedu.address.commons.util.IntegerUtil.getStringFromInt;
import static seedu.address.commons.util.StockUtil.determineStockHealthColor;

import java.util.Comparator;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.item.SupplyItem;

/**
 * An UI component that displays information of a {@code SupplyItem}.
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
    private final Consumer<Integer> increaseHandler;
    private final Consumer<Integer> decreaseHandler;
    private final Consumer<Integer> changeIncDecHandler;

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
    @FXML
    private Button increase;
    @FXML
    private Button decrease;
    @FXML
    private TextField amountInput;

    /**
     * Creates a {@code SupplyItemCard} with the given {@code SupplyItem} and index to display.
     */
    public SupplyItemCard(SupplyItem supplyItem, int displayedIndex, Consumer<Integer> increaseHandler,
                          Consumer<Integer> decreaseHandler, Consumer<Integer> changeIncDecHandler) {
        super(FXML);
        this.supplyItem = supplyItem;
        this.increaseHandler = increaseHandler;
        this.decreaseHandler = decreaseHandler;
        this.changeIncDecHandler = changeIncDecHandler;
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
        amountInput.setText(String.valueOf(supplyItem.getIncDecAmount()));
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

    /**
     * Handles the increase of SupplyItem in the Inventory.
     */
    @FXML
    private void handleIncrease() {
        increaseHandler.accept(supplyItem.getIncDecAmount());
    }

    /**
     * Handles the decrease of SupplyItem in the Inventory.
     */
    @FXML
    private void handleDecrease() {
        decreaseHandler.accept(supplyItem.getIncDecAmount());
    }

    /**
     * Handles the amount of SupplyItem to be increased/decreased in the user input.
     */
    @FXML
    private void handleAmount() {
        String input = amountInput.getText();
        try {
            int parsedAmount = Integer.parseInt(input);
            if (parsedAmount >= 0) {
                changeIncDecHandler.accept(parsedAmount);
            } else {
                amountInput.setText(String.valueOf(supplyItem.getIncDecAmount()));
            }
        } catch (NumberFormatException e) {
            amountInput.setText(String.valueOf(supplyItem.getIncDecAmount()));
        }
    }
}
