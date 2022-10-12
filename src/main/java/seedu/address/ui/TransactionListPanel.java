package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.transaction.Transaction;

/**
 * Panel containing the list of pocs.
 */
public class TransactionListPanel extends UiPart<Region> {
    private static final String FXML = "TransactionPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PocListPanel.class);

    @FXML
    private ListView<Transaction> transactionListView;

    /**
     * Creates a {@code PocListPanel} with an empty {@code ObservableList}.
     */
    public TransactionListPanel() {
        super(FXML);
    }

    /**
     * Sets the {@code PocListPanel} with the pocs from {@code ObservableList}.
     */
    public void setTransactionList(ObservableList<Transaction> transactionList) {
        requireNonNull(transactionList);

        transactionListView.setItems(transactionList);
        transactionListView.setCellFactory(listView -> new TransactionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Poc} using a {@code PocCard}.
     */
    class TransactionListViewCell extends ListCell<Transaction> {
        @Override
        protected void updateItem(Transaction transaction, boolean empty) {
            super.updateItem(transaction, empty);

            if (empty || transaction == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransactionCard(transaction, getIndex() + 1).getRoot());
            }
        }
    }

}
