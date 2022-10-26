package seedu.intrack.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;

import java.awt.Label;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * An UI component that displays information of the most recent upcoming {@code Task}.
 */
public class UpcomingTaskCard extends UiPart<Region> {

    private static final String FXML = "UpcomingTaskCard.fxml";

    public final LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    public final Internship internship;

    @FXML
    private HBox cardPane;

    @FXML
    private Label upcomingTask;

    /**
     * Creates a {@UpcomingTaskCard} with the given most recent upcoming {@code Task} to display.
     */
    public UpcomingTaskCard(Internship internship) {
        super(FXML);

        this.internship = internship;
        String nextTask = internship.getTasks().stream()
                .filter(task -> task.getTaskTime().isAfter(currentDateTime))
                .map(Task::toString)
                .findFirst().orElse("");

        upcomingTask.setText(nextTask);
    }
}
