package seedu.address.ui.listpanels;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Supplier;
import seedu.address.ui.UiPart;
import seedu.address.ui.displaycards.SupplierCard;


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
    public SupplierListPanel(ObservableList<Supplier> supplierList, Logic logic) {
        super(FXML);
        supplierListView.setItems(supplierList);
        supplierListView.setCellFactory(listView -> new SupplierListViewCell(logic));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Supplier} using a {@code SupplierCard}.
     */
    private static class SupplierListViewCell extends ListCell<Supplier> {

        private final Logic logic;

        public SupplierListViewCell(Logic logic) {
            this.logic = logic;
        }

        @Override
        protected void updateItem(Supplier supplier, boolean empty) {
            super.updateItem(supplier, empty);

            if (empty || supplier == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SupplierCard(supplier, getIndex() + 1, logic).getRoot());
            }
        }
    }


}
