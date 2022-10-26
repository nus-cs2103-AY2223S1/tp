package seedu.uninurse.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.uninurse.model.person.Patient;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";

    @FXML
    private ListView<Patient> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Patient> personList) {
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
        }

        @Override
        protected void updateItem(Patient person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TruncatedPersonListCard(person, getIndex() + 1).getRoot());
                //setGraphic(new PersonListCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
