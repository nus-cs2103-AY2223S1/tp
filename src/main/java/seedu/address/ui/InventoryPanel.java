package seedu.address.ui;

import java.util.function.Consumer;
import java.util.function.Function;
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

    private final Function<Integer, Consumer<Integer>> increaseHandler;
    private final Function<Integer, Consumer<Integer>> decreaseHandler;
    private final Function<Integer, Consumer<Integer>> changeIncDecHandler;

    @FXML
    private ListView<SupplyItem> inventoryView;

    /**
     * Creates a {@code InventoryPanel} with the given {@code ObservableList}.
     */
    public InventoryPanel(ObservableList<SupplyItem> supplyItemList,
                          Function<Integer, Consumer<Integer>> increaseHandler,
                          Function<Integer, Consumer<Integer>> decreaseHandler,
                          Function<Integer, Consumer<Integer>> changeIncDecHandler) {
        super(FXML);
        this.increaseHandler = increaseHandler;
        this.decreaseHandler = decreaseHandler;
        this.changeIncDecHandler = changeIncDecHandler;
        inventoryView.setItems(supplyItemList);
        inventoryView.setCellFactory(listView -> new InventoryViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code SupplyItem} using a {@code SupplyItemCard}.
     */
    class InventoryViewCell extends ListCell<SupplyItem> {
        @Override
        protected void updateItem(SupplyItem item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SupplyItemCard(item, getIndex() + 1, increaseHandler.apply(getIndex()),
                        decreaseHandler.apply(getIndex()), changeIncDecHandler.apply(getIndex())).getRoot());
            }
        }
    }

}
