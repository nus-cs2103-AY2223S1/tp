package seedu.waddle.ui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.waddle.commons.core.LogsCenter;
import seedu.waddle.model.item.Item;

/**
 * Panel containing the list of Items.
 */
public class ItemListPanel extends ListPanel {
    private static final String FXML = "ItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemListPanel.class);
    private final int dayNumber;

    @FXML
    private ListView<Item> itemListView;

    /**
     * Creates a {@code ItemListPanel} with the given {@code ObservableList}.
     */
    public ItemListPanel(ObservableList<Item> itemList, int dayNumber) {
        super(FXML);
        this.dayNumber = dayNumber;
        itemListView.setItems(itemList);
        itemListView.setCellFactory(listView -> new ItemListPanel.ItemListViewCell());
        itemListView.prefHeightProperty().bind(Bindings.size(itemList).multiply(UiSizes.ITEM_CARD_HEIGHT));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Item} using a {@code ItemCard}.
     */
    class ItemListViewCell extends ListCell<Item> {
        @Override
        protected void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItemCard(item, dayNumber, getIndex() + 1).getRoot());
            }
        }
    }

}
