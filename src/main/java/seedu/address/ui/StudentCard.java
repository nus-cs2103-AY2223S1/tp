package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;
import seedu.address.storage.ImageStorage;

/**
 * An UI component that displays information of a {@code Student}.
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
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label studentId;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label classGroup;
    @FXML
    private ImageView picture;
    @FXML
    private Label attendanceList;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText("Phone: " + student.getPhone().value);
        studentId.setText("Student ID: " + student.getStudentId().value);
        email.setText("Email: " + student.getEmail().value);
        attendanceList.setText("Attendance: " + student.getAttendanceList().toString());
        classGroup.setText("Class: " + student.getClassGroup().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        updatePicture();

        // Set wrap
        name.setWrapText(true);
        email.setWrapText(true);
        attendanceList.setWrapText(true);
        classGroup.setWrapText(true);
    }

    /**
     * Update the Picture of the Student.
     */
    public void updatePicture() {
        picture.setImage(
                ImageStorage.getStudentProfileImage(this.student)
        );

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
