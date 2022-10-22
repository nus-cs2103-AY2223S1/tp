package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.student.Student;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class StudentDescription extends UiPart<Region> {

    private static final String FXML = "StudentDescription.fxml";

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
    protected VBox assignedClassList;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentDescription(Student student) {
        super(FXML);
        this.student = student;
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        level.setText(student.getLevel().level);
        school.setText(student.getSchool().school);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        student.getTuitionClasses().stream()
                .sorted(Comparator.comparing(tuitionClass -> tuitionClass.getName().name))
                .forEach(tuitionClass -> assignedClassList.getChildren().add(
                        new AssignedClass(tuitionClass.getName().name).getRoot()));
    }

    public Student getDisplayedStudent() {
        return this.student;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentDescription)) {
            return false;
        }

        // state check
        StudentDescription card = (StudentDescription) other;
        return student.equals(card.student);
    }
}
