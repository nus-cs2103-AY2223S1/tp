package seedu.taassist.ui;

import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.model.session.SessionData;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentView;

/**
 * An UI component that displays information of a {@code StudentView}.
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

    public final StudentView studentView;
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label grade;
    @FXML
    private FlowPane classes;

    /**
     * Creates a {@code StudentCard} with the given {@code StudentView} and index to display.
     */
    public StudentCard(StudentView studentView, int displayedIndex) {
        super(FXML);

        this.studentView = studentView;

        // Setting Student identity
        Student student = studentView.getStudent();
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        student.getModuleClasses().stream()
                .sorted(Comparator.comparing(moduleClass -> moduleClass.getClassName()))
                .forEach(moduleClass -> classes.getChildren().add(new Label(moduleClass.getClassName())));

        // Setting SessionData information
        grade.setText("");
        if (studentView.hasSession()) {
            Optional<SessionData> sessionData = studentView.getSessionData();
            if (sessionData.isEmpty()) {
                cardPane.getStyleClass().add("not-graded");
            } else {
                grade.setText(String.valueOf(sessionData.get().getGrade()));
            }
        }
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
                && studentView.equals(card.studentView);
    }
}
