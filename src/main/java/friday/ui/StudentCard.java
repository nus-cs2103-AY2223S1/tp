package friday.ui;

import java.util.Comparator;

import friday.model.student.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Student}.
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

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label telegramHandle;
    @FXML
    private Label masteryCheck;
    @FXML
    private Label consultation;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;
    @FXML
    private Label ra1;
    @FXML
    private Label ra2;
    @FXML
    private Label pa;
    @FXML
    private Label mt;
    @FXML
    private Label ft;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        telegramHandle.setText(String.format("Telegram: %s", student.getTelegramHandle().toString()));
        masteryCheck.setText(student.getMasteryCheck().toString());
        consultation.setText(student.getConsultation().toString());
        remark.setText(student.getRemark().toString());
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        ra1.setText(student.getGradesList().getGrade("RA1").getScore() + " %");
        ra2.setText(student.getGradesList().getGrade("RA2").getScore() + " %");
        pa.setText(student.getGradesList().getGrade("Practical").getScore() + " %");
        mt.setText(student.getGradesList().getGrade("Midterm").getScore() + " %");
        ft.setText(student.getGradesList().getGrade("Finals").getScore() + " %");
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
