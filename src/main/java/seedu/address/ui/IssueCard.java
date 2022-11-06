package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.issue.Issue;

/**
 * An UI component that displays information of a {@code Issue}.
 */
public class IssueCard extends UiPart<Region> {

    private static final String FXML = "IssueListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Issue issue;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label urgency;
    @FXML
    private Label deadline;
    @FXML
    private Label project;
    @FXML
    private Label status;

    /**
     * Creates a {@code IssueCard} with the given {@code Issue} and index to display.
     */
    public IssueCard(Issue issue) {
        super(FXML);
        this.issue = issue;
        String issueIdString = issue.getIssueId().uiRepresentation();
        title.setText(issue.getTitle().uiRepresentation(issue.isPinned(), issueIdString));
        deadline.setText(issue.getDeadline().uiRepresentation());
        urgency.setText(issue.getUrgency().uiRepresentation());
        project.setText(issue.getProject().uiRepresentation());
        status.setText(issue.getStatus().uiRepresentation());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IssueCard)) {
            return false;
        }

        // state check
        IssueCard card = (IssueCard) other;
        return id.getText().equals(card.id.getText())
                && issue.equals(card.issue);
    }
}


