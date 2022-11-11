package tracko.ui;

import java.util.logging.Logger;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import tracko.commons.core.LogsCenter;

/**
 * Display box containing the two list of orders and items.
 */
public class DisplayBox extends UiPart<Region> {
    private static final String FXML = "DisplayBox.fxml";

    private final Logger logger = LogsCenter.getLogger(DisplayBox.class);

    @javafx.fxml.FXML
    private HBox listPlaceholder;

    /**
     * Creates a {@code DisplayBox} with the list components.
     */
    public DisplayBox(OrderListPanel orderListPanel, ItemListPanel itemListPanel) {
        super(FXML);
        HBox.setHgrow(orderListPanel.getRoot(), Priority.ALWAYS);
        HBox.setHgrow(itemListPanel.getRoot(), Priority.ALWAYS);
        listPlaceholder.getChildren().addAll(orderListPanel.getRoot(), itemListPanel.getRoot());
        listPlaceholder.setSpacing(10);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DisplayBox)) {
            return false;
        }

        // state check
        DisplayBox box = (DisplayBox) other;
        return listPlaceholder.equals(box.listPlaceholder);
    }

}
