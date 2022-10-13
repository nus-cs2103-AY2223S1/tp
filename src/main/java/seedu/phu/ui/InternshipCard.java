package seedu.phu.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.phu.model.internship.Internship;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    private static final String FXML = "InternshipListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/internshipbook-level4/issues/336">The issue on InternshipBook level 4</a>
     */

    public final Internship internship;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label remark;
    @FXML
    private Label email;
    @FXML
    private Label position;
    @FXML
    private Label applicationProcess;
    @FXML
    private Label date;
    @FXML
    private Label website;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        id.setText(displayedIndex + ". ");
        name.setText(internship.getName().fullName);
        phone.setText("phone: " + internship.getPhone().value);
        remark.setText("remark: " + internship.getRemark().value);
        email.setText("email: " + internship.getEmail().value);
        position.setText("position: " + internship.getPosition().positionName);
        applicationProcess.setText("application process: " + internship.getApplicationProcess().toString());
        date.setText("date: " + internship.getDate().toDisplayFormat());
        website.setText("website: " + internship.getWebsite().value);
        internship.getTags().stream()
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
        if (!(other instanceof InternshipCard)) {
            return false;
        }

        // state check
        InternshipCard card = (InternshipCard) other;
        return id.getText().equals(card.id.getText())
                && internship.equals(card.internship);
    }
}
