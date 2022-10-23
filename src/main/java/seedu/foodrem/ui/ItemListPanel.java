package seedu.foodrem.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.foodrem.model.item.Item;

/**
 * Panel containing the list of items.
 */
public class ItemListPanel extends UiPart<Region> {
    @FXML
    private ListView<Item> itemListView;

    /**
     * Creates a {@code ItemListPanel} with the given {@code ObservableList}.
     */
    public ItemListPanel(ObservableList<Item> itemList) {
        super("ItemListPanel.fxml");
        itemListView.setItems(itemList);
        itemListView.setCellFactory(listView -> new ItemListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Item} using a {@code ItemCard}.
     */
    static class ItemListViewCell extends ListCell<Item> {
        @Override
        protected void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItemCard(item, getIndex() + 1).getRoot());
            }
        }
    }
}
