package seedu.intrack.ui;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.intrack.commons.core.LogsCenter;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Panel containing the most recent upcoming Task.
 */
public class UpcomingTaskPanel extends UiPart<Region> {

    private static final String FXML = "UpcomingTaskPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(UpcomingTaskPanel.class);

    public final LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    @FXML
    private ListView<Task> taskListView;

    public UpcomingTaskPanel(ObservableList<Internship> internshipList) {
        super(FXML);
        ObservableList<Task> upcomingTask = internshipList.stream().map(internship -> internship.getTasks().get(0))
                .filter(task -> task.getTaskTime().isAfter(currentDateTime))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        taskListView.setItems(upcomingTask);
    }
}
