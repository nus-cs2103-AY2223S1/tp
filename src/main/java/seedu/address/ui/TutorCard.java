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
 * An UI component that displays information of a {@code Tutor}.
 */
public class TutorCard extends UiPart<Region> {
    private static final String FXML = "TutorListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ProfNus level 4</a>
     */

    public final Student tutor;

    @javafx.fxml.FXML
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
    private FlowPane studentInfo;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public TutorCard(Student tutor, int displayedIndex) {
        super(FXML);
        this.tutor = tutor;
        id.setText(displayedIndex + ". ");
        name.setText(tutor.getName().fullName);
        address.setText("Address: " + tutor.getAddress().value);
        email.setText("Email: " + tutor.getEmail().value);
        contactInfo.setText("Phone: " + tutor.getPhone().value + " Telegram: " + tutor.getTelegramHandle());
        studentID.setText("(" + tutor.getId() + ")");
        for (ModuleCode code: tutor.getTeachingAssistantInfo()) {
            Label temp = new Label(code.fullCode);
            temp.setStyle("-fx-text-fill: white; -fx-background-color: #097969;-fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            studentInfo.getChildren().add(temp);
        }
        for (String classGroup: tutor.getClassGroups()) {
            Label temp = new Label(classGroup);
            temp.setStyle("-fx-text-fill: white; -fx-background-color: #fb7600;-fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
            studentInfo.getChildren().add(temp);
        }
        for (Tag tag: tutor.getTags()) {
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
        if (!(other instanceof TutorCard)) {
            return false;
        }

        // state check
        TutorCard card = (TutorCard) other;
        return id.getText().equals(card.id.getText())
                && tutor.equals(card.tutor);
    }
}
