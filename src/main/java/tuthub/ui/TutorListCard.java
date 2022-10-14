package tuthub.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import tuthub.model.tutor.Tutor;

/**
 * An UI component that displays information of a {@code Tutor}.
 */
public class TutorListCard extends UiPart<Region> {

    private static final String FXML = "TutorListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/tuthub-level4/issues/336">The issue on Tuthub level 4</a>
     */

    public final Tutor tutor;
    private final String studentId;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label module;
//    @FXML
//    private Label year;
//    @FXML
//    private Label phone;
//    @FXML
//    private Label email;
//    @FXML
//    private Label comment;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code TutorCode} with the given {@code Tutor} and index to display.
     */
    public TutorListCard(Tutor tutor, int displayedIndex) {
        super(FXML);
        this.tutor = tutor;
        this.studentId = tutor.getStudentId().value;

        id.setText(displayedIndex + ". ");
        name.setText(tutor.getName().fullName);
        module.setText(tutor.getModule().value);
//        year.setText("year " + tutor.getYear().value);
//        phone.setText(tutor.getPhone().value);
//        email.setText(tutor.getEmail().value);
//        comment.setText(tutor.getComment().value);
        tutor.getTags().stream()
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
        if (!(other instanceof TutorListCard)) {
            return false;
        }

        // state check
        TutorListCard card = (TutorListCard) other;
        return studentId.equals(card.studentId)
                && tutor.equals(card.tutor);
    }
}
