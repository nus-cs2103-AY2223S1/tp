package tracko.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tracko.commons.core.LogsCenter;
import tracko.model.items.Item;

/**
 * Panel containing the list of orders.
 */
public class ItemListPanel extends UiPart<Region> {
    private static final String FXML = "ItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemListPanel.class);

    @javafx.fxml.FXML
    private ListView<Item> itemListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ItemListPanel(ObservableList<Item> itemList) {
        super(FXML);
        itemListView.setItems(itemList);
        itemListView.setCellFactory(listView -> new ItemListViewCell(){{
            setStyle("-fx-background-insets: 3px;");
        }});
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Item} using an {@code ItemCard}.
     */
    class ItemListViewCell extends ListCell<Item> {
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
