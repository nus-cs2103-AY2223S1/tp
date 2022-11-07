package seedu.condonery.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.model.property.Property;

/**
 * Panel containing the list of properties.
 */
public class PropertyListPanel extends UiPart<Region> {
    private static final String FXML = "PropertyListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PropertyListPanel.class);

    @FXML
    private ListView<Property> propertyListView;

    /**
     * Creates a {@code PropertyListPanel} with the given {@code ObservableList}.
     */
    public PropertyListPanel(ObservableList<Property> propertyList) {
        super(FXML);
        propertyListView.setItems(propertyList);
        propertyListView.setCellFactory(listView -> new PropertyListViewCell());
    }

    /**
     * Forces re-render of the PropertyListPanel.
     */
    public void refresh() {
        propertyListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Property} using a {@code PropertyCard}.
     */
    class PropertyListViewCell extends ListCell<Property> {
        @Override
        protected void updateItem(Property property, boolean empty) {
            super.updateItem(property, empty);

            if (empty || property == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PropertyCard(property, getIndex() + 1).getRoot());
            }
        }
    }

}
