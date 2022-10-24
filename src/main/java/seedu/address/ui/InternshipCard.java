package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.Internship;

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
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;

    @FXML
    private HBox cardPane;
    @FXML
    private Text company;
    @FXML
    private Label id;
    @FXML
    private Button linkButton;
    @FXML
    private Label appliedDateLabel;
    @FXML
    private Label appliedDate;
    @FXML
    private Label interviewDateTimeLabel;
    @FXML
    private Label interviewDateTime;
    @FXML
    private Label applicationStatus;
    @FXML
    private Label description;
    @FXML
    private VBox tags;

    /**
     * Creates a {@code InternshipCard} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        String applicationStatusString = internship.getApplicationStatus().toString();

        id.setText(displayedIndex + ". ");
        company.setText(internship.getCompany().value);
        linkButton.setText(internship.getLink().value);
        appliedDateLabel.setText("Applied:");

        interviewDateTimeLabel.setText("Interview date/time:");
        interviewDateTimeLabel.setMinWidth(Region.USE_PREF_SIZE);
        if (internship.getInterviewDateTime() == null) {
            interviewDateTime.setText("");
        } else {
            interviewDateTime.setText(internship.getInterviewDateTime().value);
        }
        interviewDateTime.setMinWidth(Region.USE_PREF_SIZE);

        appliedDate.setText(internship.getAppliedDate().value);
        applicationStatus.setText(applicationStatusString);
        description.setText(internship.getDescription().value);

        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        linkButton.setTooltip(new Tooltip("Copy link"));

        applicationStatus.getStyleClass().add(applicationStatusString.toLowerCase());
        applicationStatus.setMinWidth(Region.USE_PREF_SIZE);
        if (internship.getApplicationStatus() == ApplicationStatus.Shortlisted) {
            applicationStatus.setTooltip(new Tooltip("Shortlisted for interview"));
        }
    }

    /**
     * Copies URL of internship to the clipboard. Repurposed from HelpWindow class.
     */
    @FXML
    private void copyInternshipUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(linkButton.getText());
        clipboard.setContent(url);
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
