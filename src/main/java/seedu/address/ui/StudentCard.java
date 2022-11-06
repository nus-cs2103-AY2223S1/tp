package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.student.HelpTag;
import seedu.address.model.student.Student;

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

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label telegram;
    @FXML
    private Label email;
    @FXML
    private Label response;
    @FXML
    private Label attendance;
    @FXML
    private Label helpTag;


    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        email.setText(student.getEmail().value);
        telegram.setText(student.getTelegram().telegram);
        response.setText("Response: " + student.getResponse().value);
        attendance.setText("Attendance: " + student.getAttendance().attendance);
        if (student.needsHelp()) {
            applyStyleToHelpTag();
        } else {
            helpTag.setVisible(false);
        }
    }

    private void applyStyleToHelpTag() {
        helpTag.setText(HelpTag.HELP);

        //to be transferred to DarkTheme.css if possible
        helpTag.setBackground(new Background(
                new BackgroundFill(Color.rgb(241, 88, 88), new CornerRadii(2), Insets.EMPTY)));
        helpTag.setPadding(new Insets(1, 5, 1, 5));
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
