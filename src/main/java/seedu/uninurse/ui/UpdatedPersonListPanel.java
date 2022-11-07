package seedu.uninurse.ui;

import static seedu.uninurse.ui.UiUtil.LIST_VIEW_OFFSET;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * Panel containing the list of updated persons.
 */
public class UpdatedPersonListPanel extends UiPart<Region> {
    private static final String FXML = "UpdatedPersonListPanel.fxml";

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a UpdatedPersonListPanel with the given ObservableList.
     */
    public UpdatedPersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom ListCell that displays the graphics of a Patient using a UpdatedPatientCard.
     */
    class PersonListViewCell extends ListCell<Person> {
        PersonListViewCell() {
            super();
            setStyle("-fx-padding: 5 5 5 0");
            prefWidthProperty().bind(personListView.widthProperty().subtract(LIST_VIEW_OFFSET));
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
                    setGraphic(new UpdatedPatientCard((Patient) person, "", false).getRoot());
                }
            }
        }
    }
}
