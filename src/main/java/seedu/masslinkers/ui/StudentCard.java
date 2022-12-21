package seedu.masslinkers.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.masslinkers.model.student.Student;

/**
 *  A UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/massLinkers-level4/issues/336">The issue on MassLinkers level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private HBox phoneContainer;
    @FXML
    private Label phone;
    @FXML
    private HBox emailContainer;
    @FXML
    private Label email;
    @FXML
    private HBox telegramContainer;
    @FXML
    private Label telegram;
    @FXML
    private HBox githubContainer;
    @FXML
    private Label github;
    @FXML
    private FlowPane interests;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        telegram.setText(student.getTelegram().handle);
        if (student.getPhone() != null) {
            phone.setText(student.getPhone().value);
        } else {
            phoneContainer.getChildren().clear();
        }
        if (student.getEmail() != null) {
            email.setText(student.getEmail().value);
        } else {
            emailContainer.getChildren().clear();
        }
        if (student.getGitHub() != null) {
            github.setText(student.getGitHub().username);
        } else {
            githubContainer.getChildren().clear();
        }

        student.getInterests()
                .stream()
                .sorted(Comparator.comparing(interest -> interest.interestName))
                .forEach(interest -> interests.getChildren().add(new Label(interest.interestName)));
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
