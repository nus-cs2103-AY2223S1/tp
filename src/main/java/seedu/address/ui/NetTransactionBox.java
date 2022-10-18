package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.model.transaction.Transaction;

import static java.util.Objects.requireNonNull;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class NetTransactionBox extends UiPart<Region> {

    private static final String FXML = "NetTransactionBox.fxml";
    private double netTransactionAmount;
    @FXML
    private TextField netTransaction;

    public NetTransactionBox(double amount) {
        super(FXML);
        netTransaction.setText("Net amount: " + amount+"");
    }

    public void setNetTransaction(double netTransactionAmount) {
        requireNonNull(netTransactionAmount);
        netTransaction.setText(netTransactionAmount+"");
    }
}
