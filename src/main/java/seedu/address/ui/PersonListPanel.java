package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.teammate.Teammate;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Teammate> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Teammate> teammateList) {
        super(FXML);
        personListView.setItems(teammateList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Teammate> {
        @Override
        protected void updateItem(Teammate teammate, boolean empty) {
            super.updateItem(teammate, empty);

            if (empty || teammate == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(teammate, getIndex() + 1).getRoot());
            }
        }
    }

}
