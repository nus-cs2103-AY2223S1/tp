package modtrekt.ui.tasks;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import modtrekt.commons.core.LogsCenter;
import modtrekt.model.task.Task;
import modtrekt.ui.UiPart;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "tasks/TaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
        taskList.addListener((ListChangeListener<? super Task>) this::handleChange);
    }

    /**
     * Handles change in the list of modules by highlighting the TaskCard addition to the list, if any.
     */
    private void handleChange(ListChangeListener.Change<? extends Task> change) {
        taskListView.getSelectionModel().clearSelection();
        taskListView.getFocusModel().focus(-1);
        while (change.next()) {
            logger.fine(change.toString());
            if (!taskListView.getItems().isEmpty() && change.wasAdded() && change.getAddedSize() == 1) {
                // We only care about additions of size 1 because those are the only kinds of changes we
                // should set selection and focus to (multi-selection doesn't make sense because filtering (e.g. ls -a)
                // affects the entire list).
                // We ignore removals because there's nothing to focus and select after it's removed.
                int changeIndex = change.getFrom();
                taskListView.scrollTo(changeIndex);
                taskListView.getSelectionModel().select(changeIndex);
                taskListView.getFocusModel().focus(changeIndex);
                return;
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Module} using a {@code ModuleCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task t, boolean empty) {
            super.updateItem(t, empty);

            if (empty || t == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(t, getIndex() + 1).getRoot());
            }
        }
    }

}
