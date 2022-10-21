package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

import java.util.logging.Logger;

/**
 * Panel containing the list of persons.
 */
public class NoteListPanel extends UiPart<Region> {
    private static final String FXML = "NoteListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    @FXML
    private ListView<Person> noteListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public NoteListPanel(ObservableList<Person> personList) {
        super(FXML);
        assert noteListView == null;
        noteListView.setItems(personList);
        noteListView.setCellFactory(listView -> new NoteListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class NoteListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
