package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.property.Property;


/**
 * Panel containing the list of persons.
 */
public class PropertyListPanel extends UiPart<Region> {
    private static final String FXML = "PropertyListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PropertyListPanel.class);

    @FXML
    private ListView<Property> propertyListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PropertyListPanel(ObservableList<Property> propertyList) {
        super(FXML);
        propertyListView.setItems(propertyList);
        propertyListView.setCellFactory(listView -> new PropertyListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
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
