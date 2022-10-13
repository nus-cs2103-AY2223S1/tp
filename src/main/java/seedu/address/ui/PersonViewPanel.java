package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the detailed view of a person.
 */
public class PersonViewPanel extends UiPart<Region> {
    private static final String FXML = "PersonViewPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonViewPanel.class);

    @FXML
    private ListView<Person> personView;

    /**
     * Creates a {@code PersonViewPanel} with the given {@code ObservableList}.
     */
    public PersonViewPanel(ObservableList<Person> personList) {
        super(FXML);
        personView.setItems(personList);
        personView.setCellFactory(listView -> new PersonViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonViewCard}.
     */
    class PersonViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonViewCard(person).getRoot());
            }
        }
    }
}
