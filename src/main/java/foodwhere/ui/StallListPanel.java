package foodwhere.ui;

import java.util.logging.Logger;

import foodwhere.commons.core.LogsCenter;
import foodwhere.model.person.Person;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of persons.
 */
public class StallListPanel extends UiPart<Region> {
    private static final String FXML = "StallListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StallListPanel.class);

    @FXML
    private ListView<Person> stallListView;

    /**
     * Creates a {@code StallListPanel} with the given {@code ObservableList}.
     */
    public StallListPanel(ObservableList<Person> personList) {
        super(FXML);
        stallListView.setItems(personList);
        stallListView.setCellFactory(listView -> new StallListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code StallCard}.
     */
    class StallListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StallCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
