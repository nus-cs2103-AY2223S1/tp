package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;


/**
 * The UI component that is responsible for displaying the total amount transacted with clients in the displayed list.
 */
public class NetTransactionBox extends UiPart<Region> {
    public static final String LABEL = "Net amount: $";

    private static final String FXML = "NetTransactionBox.fxml";
    private double netTransactionAmount;
    @FXML
    private TextField netTransaction;

    /**
     * Creates a new NetTransactionBox.
     *
     * @param amount to be stored as the calculated total amount.
     */
    public NetTransactionBox(double amount) {
        super(FXML);
        this.netTransactionAmount = amount;
        netTransaction.setText(LABEL + Double.toString(amount));
    }

    /**
     * Sets the updated net transaction amount.
     *
     * @param netTransactionAmount double value to be used to update the box.
     */
    public void setNetTransaction(double netTransactionAmount) {
        this.netTransactionAmount = netTransactionAmount;
        requireNonNull(netTransactionAmount);
        netTransaction.setText(LABEL + Double.toString(netTransactionAmount));
    }
}
