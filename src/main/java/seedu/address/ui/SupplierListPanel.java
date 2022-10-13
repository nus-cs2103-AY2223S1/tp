package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Supplier;


/**
 * Panel containing the list of Suppliers.
 */
public class SupplierListPanel extends UiPart<Region> {
    private static final String FXML = "SupplierListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(SupplierListPanel.class);

    @FXML
    private ListView<Supplier> supplierListView;


    /**
     * Creates a {@code SupplierListPanel} with the given {@code ObservableList}.
     */
    public SupplierListPanel(ObservableList<Supplier> supplierList) {
        super(FXML);
        supplierListView.setItems(supplierList);
        supplierListView.setCellFactory(listView -> new SupplierListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Supplier} using a {@code SupplierCard}.
     */
    class SupplierListViewCell extends ListCell<Supplier> {
        @Override
        protected void updateItem(Supplier supplier, boolean empty) {
            super.updateItem(supplier, empty);

            if (empty || supplier == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SupplierCard(supplier, getIndex() + 1).getRoot());
            }
        }
    }
}
