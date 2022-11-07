package seedu.watson.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.watson.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Database level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label studentClass;
    @FXML
    private Label attendance;
    @FXML
    private Label grade;
    @FXML
    private Label subjects;
    @FXML
    private Label blankLine;
    @FXML
    private FlowPane tags;
    @FXML
    private Label remarks;

    /**
     * Creates a {@code PersonCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        name.setText(displayedIndex + ". " + student.getName().fullName);
        phone.setText("Phone: " + student.getPhone().value);
        address.setText("Address: " + student.getAddress().value);
        email.setText("Email: " + student.getEmail().value);
        studentClass.setText("Class: " + student.getStudentClass().toString());
        remarks.setText("Remarks: " + student.getRemarksString());
        attendance.setText("Attendance: " + student.getAttendance().guiString());
        if (student.getAttendance().hasGoodAttendance()) {
            attendance.setBackground(new Background(new BackgroundFill(Color.GREEN,
                                                                       new CornerRadii(3), null)));
        } else {
            attendance.setBackground(new Background(new BackgroundFill(Color.RED,
                                                                       new CornerRadii(3), null)));
        }

        subjects.setText(student.getSubjectHandler().toString());
        blankLine = new Label();
        student.getTags().stream()
              .sorted(Comparator.comparing(tag -> tag.tagName))
              .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
        return student.equals(card.student);
    }
}
