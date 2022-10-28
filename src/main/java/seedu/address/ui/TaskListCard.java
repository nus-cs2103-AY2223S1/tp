package seedu.address.ui;

import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeKey;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskListCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    public final Task task;

    @FXML
    private VBox cardPane; // Think of this as the entire TaskListCard.
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label deadline;
    @FXML
    private Label completion;
    private boolean isExpanded;
    @FXML
    private VBox optionalInfo; // This is the optional information to be displayed.
    @FXML
    private Label studentsHeading;
    @FXML
    private Label studentsBodyText;

    /**
     * Creates a {@code TaskListCard} with the given {@code Task} and index to display.
     */
    public TaskListCard(Task task, int displayedIndex, boolean isExpanded) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ".");
        name.setText(String.valueOf(task.getTaskName()));
        description.setText(String.valueOf(task.getTaskDescription()));
        deadline.setText(String.format("Due by %s", task.getTaskDeadline()));

        Set<Student> setOfStudents = task.getStudents();
        final var gradesMap = Task.getGradesMap();

        if (setOfStudents.size() == 0) {

            completion.setText("No students are assigned to this task.");
            studentsHeading.setVisible(false);
            studentsBodyText.setVisible(false);
        } else {

            // Calculate how many percent complete this task is.
            int denominator = setOfStudents.size();
            assert denominator > 0;
            int numerator = 0;

            for (Student student : setOfStudents) {
                if (gradesMap.get(new GradeKey(student, task)) == Grade.GRADED) {
                    numerator += 1;
                }
            }
            int percentageCompletion = 100 * numerator / denominator;
            completion.setText(
                    String.format("%d%% completed (%d/%d) students", percentageCompletion, numerator, denominator)
            );


            studentsHeading.setText("List of Students:");

            // For each student, display whether the task is complete for this student.
            StringBuilder studentsBodyTextString = new StringBuilder();
            for (Student student : setOfStudents) {
                boolean taskCompletedForThisStudent = gradesMap.get(new GradeKey(student, task)) == Grade.GRADED;
                studentsBodyTextString.append(String.format(
                        "[%s] %s\n", taskCompletedForThisStudent ? "X" : " ", student.getName())
                );
            }
            studentsBodyText.setText(studentsBodyTextString.toString());
        }



        this.isExpanded = isExpanded;
        // Use the isExpanded parameter to determine whether to (immediately) show the optional information.
        onCardClicked();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskListCard)) {
            return false;
        }

        // state check
        TaskListCard card = (TaskListCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }

    /**
     * When the {@code TaskListCard} is clicked, it toggles between showing and not showing the students for whom
     * the {@code Task} has been completed. However, if no {@code Student}s are assigned to the {@code Task}, then the
     * {@code TaskListCard} will not expand, since there is no information to display.
     */
    @FXML
    public void onCardClicked() {

        if (this.task.getStudents().size() == 0) {
            return;
        }

        isExpanded = !isExpanded;

        if (isExpanded) {
            optionalInfo.setVisible(true);
            optionalInfo.setManaged(true);
        } else {
            optionalInfo.setVisible(false);
            optionalInfo.setManaged(false);
        }
    }
}
