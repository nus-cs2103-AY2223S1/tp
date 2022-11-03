package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

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
    private Label stuId;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label telegramHandle;
    @FXML
    private Label tutorial;
    @FXML
    private Label attendance;
    @FXML
    private Label participation;
    @FXML
    private Label grade;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        stuId.setText(student.getId().id);
        phone.setText(student.getPhone().value);
        email.setText(student.getEmail().value);
        telegramHandle.setText("@" + student.getTelegram().telegram);
        tutorial.setText(student.getModuleCode().moduleCode + "-" + student.getTutorialName().fullName);
        attendance.setText("Attendance: " + student.getAttendance().value);
        participation.setText("Participation: " + student.getParticipation().value);
        grade.setText("Grade: " + student.getGrade().value);
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
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
