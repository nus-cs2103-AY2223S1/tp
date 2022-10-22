package seedu.waddle.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.waddle.commons.core.LogsCenter;
import seedu.waddle.model.item.Item;

/**
 * Panel containing the list of Items.
 */
public class ItemGroupListPanel extends ListPanel {
    private static final String FXML = "ItemGroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemGroupListPanel.class);

    @FXML
    private ListView<ObservableList<Item>> itemGroupListView;

    /**
     * Creates a {@code ItemListPanel} with the given {@code ObservableList}.
     */
    public ItemGroupListPanel(ObservableList<ObservableList<Item>> itemGroups) {
        super(FXML);
        itemGroupListView.setItems(itemGroups);
        itemGroupListView.setCellFactory(listView -> new ItemGroupListPanel.ItemGroupListViewCell());
        System.out.println("DONE CREATING GROUP LIST PANEL");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Item} using a {@code ItemCard}.
     */
    class ItemGroupListViewCell extends ListCell<ObservableList<Item>> {
        @Override
        protected void updateItem(ObservableList<Item> itemGroup, boolean empty) {
            super.updateItem(itemGroup, empty);

            if (empty || itemGroup == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItemGroupCard(itemGroup, getIndex()).getRoot());
            }
        }
    }

}
