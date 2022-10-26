package seedu.intrack.ui;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.intrack.commons.core.LogsCenter;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;

/**
 * Panel containing the most recent upcoming Task.
 */
public class UpcomingTaskPanel extends UiPart<Region> {

    private static final String FXML = "UpcomingTaskPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(UpcomingTaskPanel.class);

    private final LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code UpcomingTaskPanel} with the given {@code ObservableList}.
     */
    public UpcomingTaskPanel(ObservableList<Internship> internshipList) {
        super(FXML);

        List<Task> upcomingTaskList = new ArrayList<>();
        for (Internship internship : internshipList) {
            upcomingTaskList.addAll(internship.getTasks());
        }
        Task nextTask = upcomingTaskList.stream()
                .sorted((t1, t2) -> t1.getTaskTime().compareTo(t2.getTaskTime()))
                .filter(task -> task.getTaskTime().isAfter(currentDateTime))
                .findFirst().orElse(null);
        ObservableList<Task> upcomingTask = FXCollections.observableArrayList(nextTask);
        taskListView.setItems(upcomingTask);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code UpcomingTaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean isEmpty) {
            super.updateItem(task, isEmpty);

            if (isEmpty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new UpcomingTaskCard(task).getRoot());
            }
        }
    }
}
