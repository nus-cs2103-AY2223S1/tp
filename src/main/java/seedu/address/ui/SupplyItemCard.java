package seedu.address.ui;
import static seedu.address.commons.util.IntegerUtil.getStringFromInt;
import static seedu.address.commons.util.StockUtil.determineStockHealthColor;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.RefreshStatsCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.SupplyItem;

/**
 * An UI component that displays information of a {@code SupplyItem}.
 */
public class SupplyItemCard extends UiPart<Region> {
    private static final String FXML = "SupplyItemCard.fxml";
    private static final int MAX_LENGTH = 5;
    private static final String EMPTY_INPUT_MESSAGE = "Empty input detected. Updating terminated.";
    private final Logger logger = LogsCenter.getLogger(getClass());
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final SupplyItem supplyItem;
    private final Consumer<Integer> increaseHandler;
    private final Consumer<Integer> decreaseHandler;
    private final Consumer<Integer> changeIncDecHandler;
    private final InventoryPanel.CommandExecutor executeCommand;

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
                          Consumer<Integer> decreaseHandler, Consumer<Integer> changeIncDecHandler,
                          InventoryPanel.CommandExecutor executeCommand) {
        super(FXML);
        this.supplyItem = supplyItem;
        this.increaseHandler = increaseHandler;
        this.decreaseHandler = decreaseHandler;
        this.changeIncDecHandler = changeIncDecHandler;
        this.executeCommand = executeCommand;
        cardPane.setStyle(String.format("-fx-border-color:%s ; -fx-border-width: 0 6 0 0;",
                determineStockHealthColor(supplyItem.getCurrentStock(), supplyItem.getMinStock())));
        id.setText(displayedIndex + ". ");
        name.setText(supplyItem.getName());
        name.setWrapText(true);
        name.setPrefWidth(70);
        supplierName.setText(supplyItem.getSupplier().getName().fullName);
        supplierName.setWrapText(true);
        supplierName.setPrefWidth(360);
        currentStock.setText(getStringFromInt(supplyItem.getCurrentStock()));
        price.setText(supplyItem.getPrice().value);
        supplyItem.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        amountInput.setText(String.valueOf(supplyItem.getIncDecAmount()));
        //@@author hauchongtang-reused
        //Reused from https://stackoverflow.com/questions/15159988/javafx-2-2-textfield-maxlength
        // with minor modifications
        amountInput.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                // force numeric value by resetting to old value if exception is thrown
                Integer.parseInt(newValue);
                // force correct length by resetting to old value if longer than maxLength
                if (newValue.length() > MAX_LENGTH) {
                    amountInput.setText(oldValue);
                }
            } catch (Exception e) {
                if (newValue.length() == 0) {
                    return;
                } else {
                    amountInput.setText(oldValue);
                }
            }
        });
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
    private void handleIncrease() throws CommandException, ParseException {
        String input = amountInput.getText();

        assert input.length() <= MAX_LENGTH;

        try {
            int parsedAmount = Integer.parseInt(input);

            assert parsedAmount >= 0;

            if (parsedAmount >= 0) {
                amountInput.setText(String.valueOf(parsedAmount));
                changeIncDecHandler.accept(parsedAmount);
                increaseHandler.accept(parsedAmount);
                executeCommand.execute(RefreshStatsCommand.COMMAND_WORD);
            } else {
                // When text field is empty, nothing is executed / accepted
                amountInput.setText("");
            }
        } catch (NumberFormatException nfe) {
            logger.info(EMPTY_INPUT_MESSAGE);
        }
    }

    /**
     * Handles the decrease of SupplyItem in the Inventory.
     */
    @FXML
    private void handleDecrease() throws CommandException, ParseException {
        String input = amountInput.getText();

        assert input.length() <= MAX_LENGTH;

        try {
            int parsedAmount = Integer.parseInt(input);

            assert parsedAmount >= 0;

            if (parsedAmount >= 0) {
                amountInput.setText(String.valueOf(parsedAmount));
                changeIncDecHandler.accept(parsedAmount);
                decreaseHandler.accept(parsedAmount);
                executeCommand.execute(RefreshStatsCommand.COMMAND_WORD);
            } else {
                // When input is empty, nothing is executed / accepted
                amountInput.setText("");
            }
        } catch (NumberFormatException nfe) {
            logger.info(EMPTY_INPUT_MESSAGE);
        }
    }

    /**
     * Handles the amount of SupplyItem to be increased/decreased in the user input.
     * Limits max number length to {@code MAX_LENGTH}
     */
    @FXML
    private void handleAmount() {
        String input = amountInput.getText();

        assert input.length() <= MAX_LENGTH;

        try {
            int parsedAmount = Integer.parseInt(input);

            assert parsedAmount >= 0;

            if (parsedAmount >= 0) {
                amountInput.setText(String.valueOf(parsedAmount));
                changeIncDecHandler.accept(parsedAmount);
            }
        } catch (NumberFormatException nfe) {
            logger.info(EMPTY_INPUT_MESSAGE);
        }
    }
}
