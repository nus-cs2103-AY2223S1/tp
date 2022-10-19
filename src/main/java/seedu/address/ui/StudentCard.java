package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.student.Student;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;
    @FXML
    protected HBox cardPane;
    @FXML
    protected Label name;
    @FXML
    protected Label id;
    @FXML
    protected Label phone;
    @FXML
    protected Label address;
    @FXML
    protected Label email;
    @FXML
    protected Label level;
    @FXML
    protected Label school;
    @FXML
    protected FlowPane tags;
    @FXML
    protected FlowPane classes;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText("Phone: " + student.getPhone().value);
        address.setText("Address: " + student.getAddress().value);
        email.setText("Email: " + student.getEmail().value);
        level.setText("Level: " + student.getLevel().level);
        //nextOfKin.setText("Next of Kin: " + student.getNextOfKin().nextOfKin);
        school.setText("School: " + student.getSchool().school);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        student.getTuitionClasses().stream()
                .sorted(Comparator.comparing(tuitionClass -> tuitionClass.getName().name))
                .forEach(tuitionClass -> classes.getChildren()
                        .add(new Label(tuitionClass.getName().name.toUpperCase())));
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
