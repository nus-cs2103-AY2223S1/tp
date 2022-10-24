package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.FilterInfo;
import seedu.address.commons.SortInfo;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;


/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private TaskListInfo taskListInfo;

    @FXML
    private HBox taskListInfoPlaceholder;

    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Task> personList) {
        super(FXML);
        taskListView.setItems(personList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
        taskListInfo = new TaskListInfo();
        taskListInfoPlaceholder.getChildren().add(taskListInfo.getRoot());
    }

    public void setSortInfo(SortInfo sortInfo) {
        requireNonNull(sortInfo);
        taskListInfo.setSortInfo(sortInfo);
    }

    public void setFilterInfo(FilterInfo filterInfo) {
        requireNonNull(filterInfo);
        taskListInfo.setFilterInfo(filterInfo);
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
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }

}
