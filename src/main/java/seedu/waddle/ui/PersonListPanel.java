package seedu.waddle.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.waddle.commons.core.LogsCenter;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Itinerary> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Itinerary> itineraryList) {
        super(FXML);
        personListView.setItems(itineraryList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Itinerary> {
        @Override
        protected void updateItem(Itinerary itinerary, boolean empty) {
            super.updateItem(itinerary, empty);

            if (empty || itinerary == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(itinerary, getIndex() + 1).getRoot());
            }
        }
    }

}
