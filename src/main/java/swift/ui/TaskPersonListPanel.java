package swift.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import swift.commons.core.LogsCenter;
import swift.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class TaskPersonListPanel extends UiPart<Region> {
    private static final String FXML = "TaskPersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskPersonListPanel.class);

    @FXML
    private ListView<Person> taskPersonListView;
    @FXML
    private Label heading;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TaskPersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        taskPersonListView.setItems(personList);
        taskPersonListView.setCellFactory(listView -> new TaskPersonListViewCell());
    }

    /**
     * Change panel to show contacts assigned to task.
     */
    protected void switchToAssignedContacts() {
        heading.setText("ASSIGNED CONTACTS");
    }

    /**
     * Change panel to show all contacts.
     */
    protected void switchToAllContacts() {
        heading.setText("CONTACTS");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class TaskPersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskPersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
