package seedu.classify.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.classify.model.student.Student;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label studentName;
    @FXML
    private Label id;
    @FXML
    private Label studentId;
    @FXML
    private Label className;
    @FXML
    private VBox parentDetailsContainer;
    @FXML
    private Label parentName;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane exams;

    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex, boolean conciseInfo) {
        super(FXML);
        this.student = student;

        // Set compulsory student details
        id.setText(displayedIndex + ". ");
        studentId.setText(student.getId().toString());
        studentName.setText(student.getStudentName().fullName);
        className.setText(student.getClassName().className);

        // Set optional parent details
        if (student.getParentName().fullName == "") {
            parentName.setText("-");
        } else {
            parentName.setText(student.getParentName().fullName);
        }
        if (student.getPhone().value == "") {
            phone.setText("-");
        } else {
            phone.setText(student.getPhone().value);
        }
        if (student.getEmail().value == "") {
            email.setText("-");
        } else {
            email.setText(student.getEmail().value);
        }

        // Set exam grade details
        student.getExams().stream()
                .sorted(Comparator.comparing(exam -> exam.toString()))
                .forEach(exam -> exams.getChildren().add(new Label(exam.toString())));

        if (conciseInfo) {
            showConciseInfo();
        } else {
            showAllInfo();
        }
    }

    /**
     * Updates PersonCard to show all info
     */
    public void showAllInfo() {
        parentDetailsContainer.setManaged(true);
        parentDetailsContainer.setVisible(true);
    }

    /**
     * Updates PersonCard to only show student's name and ID
     */
    public void showConciseInfo() {
        parentDetailsContainer.setManaged(false);
        parentDetailsContainer.setVisible(false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
