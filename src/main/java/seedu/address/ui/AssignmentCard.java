package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Assignment;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Assignment}.
 **/
public class AssignmentCard extends UiPart<Region> implements TaskCard {
    private static final String FXML = "TaskListCard.fxml";

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label students;

    /**
     * Creates a {@code AssignmentCard} with the given {@code Assignment} and index to display.
     * @param task
     * @param displayedIndex
     */
    public AssignmentCard(Assignment task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle().title);
        description.setText(task.getDescription().description);
        students.setText("Students: " + task.getStudents().toString());

        // Set wrap
        title.setWrapText(true);
        description.setWrapText(true);
        students.setWrapText(true);
    }

}
