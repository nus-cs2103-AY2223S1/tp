package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.bill.Bill;
/**
 * Panel containing the list of bills.
 */
public class BillListPanel extends UiPart<Region> {
    private static final String FXML = "BillListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BillListPanel.class);

    @FXML
    private ListView<Bill> billListView;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public BillListPanel(ObservableList<Bill> bills) {
        super(FXML);
        billListView.setItems(bills);
        billListView.setCellFactory(listView -> new BillListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class BillListViewCell extends ListCell<Bill> {
        @Override
        protected void updateItem(Bill bill, boolean empty) {
            super.updateItem(bill, empty);

            if (empty || bill == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BillCard(bill, getIndex() + 1).getRoot());
            }
        }
    }

}
