package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.transaction.BuyTransaction;
import seedu.address.model.transaction.Transaction;

/**
 * A UI component that displays information of a {@code Poc}.
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

    /**
     * Creates a {@code PocCode} with the given {@code Poc} and index to display
     */
    public TransactionCard(Transaction transaction, int displayedIndex) {
        super(FXML);
        this.transaction = transaction;
        String typeOfTransaction = "Sell";
        id.setText(displayedIndex + ". ");
        good.setText(transaction.getGoods().toString());
        price.setText(transaction.getPrice().toString());
        quantity.setText(transaction.getQuantity().toString());
        if (transaction instanceof BuyTransaction) {
            typeOfTransaction = "Buy";
        }
        transactionType.getChildren().add(new Label(typeOfTransaction));
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
