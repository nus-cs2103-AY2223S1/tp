package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * TaskListPanel is a panel which represents the list of tasks.
 *
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";

    @FXML
    private ListView<Task> taskListView;

    /**
     * Constructor of the TaskListPanel. Sets the list of tasks
     * to the ListView
     *
     * @param tasks The list of tasks which will be set.
     */
    public TaskListPanel(ObservableList<Task> tasks) {
        super(FXML);
        taskListView.setItems(tasks);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }


    static class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}

