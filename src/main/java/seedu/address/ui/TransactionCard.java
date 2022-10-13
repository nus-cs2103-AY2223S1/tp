package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.transaction.BuyTransaction;
import seedu.address.model.transaction.Transaction;

/**
 * A UI component that displays information of a {@code Transaction}.
 */
public class TransactionCard extends UiPart<Region> {

    private static final String FXML = "TransactionCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on JeeqTracker level 4</a>
     */

    public final Transaction transaction;

    @FXML
    private HBox pocCardPane;
    @FXML
    private Label id;
    @FXML
    private Label good;
    @FXML
    private Label price;
    @FXML
    private Label quantity;
    @FXML
    private FlowPane transactionType;
    @FXML
    private Label buy = new Label("Buy");
    @FXML
    private Label sell = new Label("Sell");

    /**
     * Creates a {@code TransactionCode} with the given {@code Transaction} and index to display.
     */
    public TransactionCard(Transaction transaction, int displayedIndex) {
        super(FXML);
        this.transaction = transaction;
        id.setText(displayedIndex + ". ");
        good.setText(transaction.getGoods().toString());
        price.setText(transaction.getPrice().toString());
        quantity.setText(transaction.getQuantity().toString());
        Label typeOfTransaction = transaction instanceof BuyTransaction ? buy : sell;
        buy.setId("buy");
        sell.setId("sell");
        transactionType.getChildren().add(typeOfTransaction);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof TransactionCard)) {
            return false;
        }

        // state check
        TransactionCard card = (TransactionCard) other;
        return id.getText().equals(card.id.getText())
                && transaction.equals(card.transaction);
    }
}
