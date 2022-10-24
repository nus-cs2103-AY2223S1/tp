package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ProfNus level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label studentID;
    @FXML
    private Label contactInfo;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane studentModuleInfo;
    @FXML
    private FlowPane studentInfo;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        address.setText("Address: " + student.getAddress().value);
        email.setText("Email: " + student.getEmail().value);
        studentID.setText("(" + student.getId() + ")");
        contactInfo.setText("Phone: " + student.getPhone().value + " Telegram: " + student.getTelegramHandle());
        for (ModuleCode code: student.getStudentModuleInfo()) {
            Label temp = new Label(code.fullCode);
            temp.setStyle("-fx-text-fill: white; -fx-background-color: #de3163;-fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            studentInfo.getChildren().add(temp);
        }
        for (String classGroup: student.getClassGroups()) {
            Label temp = new Label(classGroup);
            temp.setStyle("-fx-text-fill: white; -fx-background-color: #fb7600;-fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            studentInfo.getChildren().add(temp);
        }
        for (Tag tag: student.getTags()) {
            Label temp = new Label(tag.tagName);
            temp.setStyle("-fx-text-fill: white; -fx-background-color: #3e7b91;-fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            studentInfo.getChildren().add(temp);
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
                && student.equals(card.student);
    }
}
