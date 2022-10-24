package jarvis.ui;

import java.util.logging.Logger;

import jarvis.commons.core.LogsCenter;
import jarvis.model.Task;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;


/**
 * Panel containing the list of tasks, fully expanded with all details shown.
 */
public class ExpandedTaskListPanel extends UiPart<Region> {
    private static final String FXML = "ExpandedTaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpandedTaskListPanel.class);

    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public ExpandedTaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code StudentCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpandedTaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }

}
