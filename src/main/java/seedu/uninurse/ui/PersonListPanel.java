package seedu.uninurse.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a PersonListPanel with the given  ObservableList.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom ListCell that displays the graphics of a Person using a PersonListCard
     * or a Patient with a TruncatedPatientListCard.
     */
    class PersonListViewCell extends ListCell<Person> {
        PersonListViewCell() {
            super();
            setStyle("-fx-padding: 5 5 5 0");
        }

        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                // Can provide a better way to check in the future.
                if (person instanceof Patient) {
                    Patient patient = (Patient) person;
                    setGraphic(new TruncatedPatientListCard(patient, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new PersonListCard(person, getIndex() + 1).getRoot());
                }
            }
        }
    }
}
