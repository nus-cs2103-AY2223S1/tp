package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.ToDo;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    private static final String TASK_HEADER = "Tasks";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private Label header;
    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     * @param taskList
     */
    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        header.setText(TASK_HEADER);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (task instanceof ToDo) {
                    ToDo toDo = (ToDo) task;
                    setGraphic(new ToDoCard(toDo, getIndex() + 1).getRoot());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    setGraphic(new DeadlineCard(deadline, getIndex() + 1).getRoot());
                }
            }
        }
    }
}
