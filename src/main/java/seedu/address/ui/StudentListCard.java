package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentListCard extends UiPart<Region> {

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
    private VBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label tutorialGroup;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags; // Contains Label objects.

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentListCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ".");
        if (student.getTutorialGroup() != null) {
            tutorialGroup.setText(student.getTutorialGroup().toString());
        } else {
            tutorialGroup.setText("No tutorial group yet");
        }
        name.setText(this.student.getName().fullName);
        phone.setText(this.student.getPhone().value);
        email.setText(this.student.getEmail().value);

        this.student.getTags().stream()
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
        if (!(other instanceof StudentListCard)) {
            return false;
        }

        // state check
        StudentListCard card = (StudentListCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
