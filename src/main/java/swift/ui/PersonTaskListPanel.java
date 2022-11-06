package swift.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import swift.commons.core.LogsCenter;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * Panel containing the list of persons.
 */
public class PersonTaskListPanel extends UiPart<Region> {
    private static final String FXML = "PersonTaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonTaskListPanel.class);

    @FXML
    private ListView<Task> personTaskListView;
    @FXML
    private Label heading;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonTaskListPanel(ObservableList<Task> taskList, ObservableList<PersonTaskBridge> bridgeList,
                               ObservableList<Person> personList) {
        super(FXML);
        personTaskListView.setItems(taskList);
        personTaskListView.setCellFactory(listView -> new PersonTaskListViewCell(bridgeList, personList));
    }

    /**
     * Change panel to show contacts assigned to task.
     */
    protected void switchToAssignedTasks() {
        heading.setText("ASSIGNED TASKS");
    }

    /**
     * Change panel to show all contacts.
     */
    protected void switchToAllTasks() {
        heading.setText("TASKS");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonTaskListViewCell extends ListCell<Task> {
        private ObservableList<PersonTaskBridge> bridgeList;
        private ObservableList<Person> personList;

        protected PersonTaskListViewCell(ObservableList<PersonTaskBridge> bridgeList,
                                   ObservableList<Person> personList) {
            this.bridgeList = bridgeList;
            this.personList = personList;
        }

        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonTaskCard(task, getIndex() + 1, bridgeList, personList).getRoot());
            }
        }
    }
}
