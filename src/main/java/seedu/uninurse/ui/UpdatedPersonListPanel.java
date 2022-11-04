package seedu.uninurse.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.model.person.Patient;

/**
 * Panel containing the list of updated persons.
 */
public class UpdatedPersonListPanel extends UiPart<Region> {
    private static final String FXML = "UpdatedPersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(UpdatedPersonListPanel.class);

    @FXML
    private ListView<Patient> personListView;

    /**
     * Creates a {@code UpdatedPersonListPanel} with the given {@code ObservableList}.
     */
    public UpdatedPersonListPanel(ObservableList<Patient> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Patient> {
        PersonListViewCell() {
            super();
            setStyle("-fx-padding: 5 5 5 0");
            prefWidthProperty().bind(personListView.widthProperty().subtract(20.0));
        }

        @Override
        protected void updateItem(Patient person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                // setGraphic(new TruncatedTaskListCard(person).getRoot());
                setGraphic(new UpdatedPatientCard(person, "").getRoot());
            }
        }
    }
}
