package seedu.intrack.ui;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.intrack.model.internship.Task;

/**
 * An UI component that displays information of the most recent upcoming {@code Task}.
 */
public class UpcomingTaskCard extends UiPart<Region> {

    private static final String FXML = "UpcomingTaskCard.fxml";

    public final LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    public final Task task;

    @FXML
    private HBox cardPane;

    @FXML
    private Label upcomingTask;

    /**
     * Creates a {@UpcomingTaskCard} with the given most recent upcoming {@code Task} to display.
     */
    public UpcomingTaskCard(Task task) {
        super(FXML);

        this.task = task;
        String nextTask = "Upcoming Task: " + task;

        upcomingTask.setText(nextTask);
        upcomingTask.setTextFill(Color.WHITE);
    }
}
