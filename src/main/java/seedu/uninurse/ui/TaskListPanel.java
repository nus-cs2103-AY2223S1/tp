package seedu.uninurse.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;

public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private Label label;

    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listview -> new TaskListViewCell());
        label.setText("Tasks:");
    }

    class TaskListViewCell extends ListCell<Task> {

        TaskListViewCell() {
            super();
        }

        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskListCard(task, getIndex() +1).getRoot());
            }

        }

    }

}
