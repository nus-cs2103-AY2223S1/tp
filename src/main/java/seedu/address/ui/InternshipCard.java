package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.Internship;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    private static final String FXML = "InternshipListCard.fxml";

    private static final int MAX_TAGS = 5;

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
    private VBox contents;
    @FXML
    private Label company;
    @FXML
    private Label id;
    @FXML
    private Button linkButton;
    @FXML
    private HBox appliedDateLine;
    @FXML
    private Label appliedDateLabel;
    @FXML
    private Label appliedDate;
    @FXML
    private HBox interviewDateTimeLine;
    @FXML
    private Label interviewDateTimeLabel;
    @FXML
    private Label interviewDateTime;
    @FXML
    private Label applicationStatus;
    @FXML
    private Label description;
    @FXML
    private HBox tags;

    /**
     * Creates a {@code InternshipCard} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        String applicationStatusString = internship.getApplicationStatus().toString();

        id.setText(displayedIndex + ". ");
        id.setMinWidth(Region.USE_PREF_SIZE);

        company.setText(internship.getCompany().value);
        linkButton.setText(internship.getLink().value);

        appliedDateLabel.setText("Applied:");
        appliedDateLabel.setMinWidth(Region.USE_PREF_SIZE);
        appliedDate.setText(internship.getAppliedDate().value);
        appliedDate.setMinWidth(Region.USE_PREF_SIZE);

        applicationStatus.setText(applicationStatusString);
        description.setText(internship.getDescription().value);

        handleTags();
        handleInterviewDateTimeLine();
        handleLinkTooltip();
        handleApplicationStatusTooltip();

        applicationStatus.getStyleClass().add(applicationStatusString.toLowerCase());
        applicationStatus.setMinWidth(Region.USE_PREF_SIZE);
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

    /**
     * Formats tag into a {@code Label} and adds a {@code Tooltip} with full tag name.
     * @param tagName The tag name.
     */
    private void formatAndAddTag(String tagName) {
        Tooltip tagTooltip = new Tooltip(tagName);
        tagTooltip.setShowDelay(TOOLTIP_DELAY);

        Label l = new Label(tagName);
        l.setTooltip(tagTooltip);

        tags.getChildren().add(l);
    }

    /**
     * Removes extra tags if more than max tags allowed, and show a count of extra tags with a Tooltip on hover.
     */
    private void consolidateExtraTags() {
        int count = 0;
        StringBuilder tooltipText = new StringBuilder();

        while (tags.getChildren().size() > MAX_TAGS) {
            int lastChildIndex = tags.getChildren().size() - 1;

            // ok to cast since all children of tags are Labels
            Label lastTag = (Label) tags.getChildren().get(lastChildIndex);

            tags.getChildren().remove(lastChildIndex);
            count++;

            tooltipText.append("[").append(lastTag.getText()).append("]");
        }

        Tooltip moreTagsTooltip = new Tooltip(tooltipText.toString());
        moreTagsTooltip.setShowDelay(TOOLTIP_DELAY);

        Label moreTagsLabel = new Label("+" + count);
        moreTagsLabel.setTooltip(moreTagsTooltip);
        moreTagsLabel.getStyleClass().add("more-tags-label");
        moreTagsLabel.setMinWidth(Region.USE_PREF_SIZE);

        tags.getChildren().add(moreTagsLabel);
    }

    /**
     * Adds tags to be displayed.
     */
    private void handleTags() {
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> formatAndAddTag(tag.tagName));

        if (tags.getChildren().size() > MAX_TAGS) {
            consolidateExtraTags();
        }
    }

    /**
     * Handles the cases for null and non-null {@code InterviewDateTime}.
     */
    private void handleInterviewDateTimeLine() {
        if (internship.getInterviewDateTime() == null) {
            // Remove interview/date time and add tags to appliedDateLine
            contents.getChildren().remove(interviewDateTimeLine);

            Pane spacerPane = new Pane();
            spacerPane.maxWidth(Double.POSITIVE_INFINITY);
            spacerPane.maxHeight(Double.NEGATIVE_INFINITY);
            HBox.setHgrow(spacerPane, Priority.ALWAYS);

            appliedDateLine.getChildren().addAll(spacerPane, tags);
        } else {
            // Add interview date/time
            interviewDateTimeLabel.setText("Interview date/time:");
            interviewDateTimeLabel.setMinWidth(Region.USE_PREF_SIZE);

            interviewDateTime.setText(internship.getInterviewDateTime().value);
            interviewDateTime.setMinWidth(Region.USE_PREF_SIZE);
        }
    }

    /**
     * Adds tooltip to internship link.
     */
    private void handleLinkTooltip() {
        Tooltip linkTooltip = new Tooltip("Copy link");
        linkTooltip.setShowDelay(TOOLTIP_DELAY);
        linkButton.setTooltip(linkTooltip);
    }

    /**
     * Adds tooltip to {@code ApplicationStatus} Shortlisted.
     */
    private void handleApplicationStatusTooltip() {
        if (internship.getApplicationStatus() == ApplicationStatus.Shortlisted) {
            Tooltip shortlistedTooltip = new Tooltip("Shortlisted for interview");
            shortlistedTooltip.setShowDelay(TOOLTIP_DELAY);
            applicationStatus.setTooltip(shortlistedTooltip);
        }
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
