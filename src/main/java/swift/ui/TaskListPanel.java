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
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private Label listPanelHeading;
    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Task> taskList, ObservableList<PersonTaskBridge> personTaskBridgeList,
                         ObservableList<Person> personList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell(personTaskBridgeList, personList));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        private ObservableList<PersonTaskBridge> personTaskBridgeList;
        private ObservableList<Person> personList;

        protected TaskListViewCell(ObservableList<PersonTaskBridge> personTaskBridgeList,
                                   ObservableList<Person> personList) {
            this.personTaskBridgeList = personTaskBridgeList;
            this.personList = personList;
        }
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1, personTaskBridgeList, personList).getRoot());
            }
        }
    }

}
