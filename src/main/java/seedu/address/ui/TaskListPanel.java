package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.team.Task;


/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {

    private static final String FXML = "TaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private Label taskCompletion;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        int numTasks = taskList.size();
        if (numTasks > 0) {
            long numCompletedTasks = taskList.stream().filter(Task::isComplete).count();
            double completionPercentage = (double) numCompletedTasks / numTasks * 100;
            taskCompletion.setText(
                    String.format("%d of %d (%,.1f%%) completed", numCompletedTasks, numTasks, completionPercentage));
        } else {
            taskCompletion.setText("No tasks added yet!");
        }
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    private static class TaskListViewCell extends ListCell<Task> {
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
