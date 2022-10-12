package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.person.student.Student;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class StudentCard extends PersonCard {

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
    protected Label level;
    @FXML
    protected Label nextOfKin;
    @FXML
    protected Label school;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(student, displayedIndex, FXML);
        this.student = student;
        level.setText(student.getLevel().level);
        nextOfKin.setText(student.getNextOfKin().nextOfKin);
        school.setText(student.getSchool().school);
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
