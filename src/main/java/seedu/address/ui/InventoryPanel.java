package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.SupplyItem;

/**
 * Panel containing the list of persons.
 */
public class InventoryPanel extends UiPart<Region> {
    private static final String FXML = "InventoryPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InventoryPanel.class);

    @FXML
    private ListView<SupplyItem> inventoryView;

    /**
     * Creates a {@code InventoryPanel} with the given {@code ObservableList}.
     */
    public InventoryPanel(ObservableList<SupplyItem> supplyItemList) {
        super(FXML);
        inventoryView.setItems(supplyItemList);
        inventoryView.setCellFactory(listView -> new InventoryViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class InventoryViewCell extends ListCell<SupplyItem> {
        @Override
        protected void updateItem(SupplyItem item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SupplyItemCard(item, getIndex() + 1).getRoot());
            }
        }
    }

}
