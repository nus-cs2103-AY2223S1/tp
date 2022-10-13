package seedu.address.ui;

import java.util.Comparator;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.commission.Commission;
import seedu.address.model.iteration.Iteration;

/**
 * An UI component that displays information of a {@code Commission}.
 */
public class CommissionCard extends UiPart<Region> {

    private static final String FXML = "CommissionListCard.fxml";
    private static final String COLOR_COMPLETED_STYLE = "-fx-background-color: #32AE46;";
    private static final String COLOR_IN_PROGRESS_STYLE = "-fx-background-color: #548DE1";
    private static final String COLOR_NOT_STARTED_STYLE = "-fx-background-color: B8B8B8";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Commission commission;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    // JavaFx Shape nodes seem to be buggy instead the ListView, using an
    // empty label to mimic a Circle seems to work
    @FXML
    private Label completionStatusCircle;
    @FXML
    private Label completionStatus;
    @FXML
    private Label deadline;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code CommissionCard} with the given {@code Commission} and index to display.
     */
    public CommissionCard(Commission commission, int displayedIndex) {
        super(FXML);
        this.commission = commission;
        id.setText(displayedIndex + ". ");
        title.setText(commission.getTitle().title);
        deadline.setText("Due " + commission.getDeadline().toString());

        commission.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label tagLabel = new Label(tag.tagName);
                    tags.getChildren().add(tagLabel);
                });

        updateProgress();
        // Because the progress depends also on the number of iterations in the commission list,
        // we might need to update the progress when the iteration list changes
        commission.getIterationList().addListener((ListChangeListener.Change<? extends Iteration> c) ->
                updateProgress());
    }

    private void updateProgress() {
        switch(commission.getCompletionStatusString()) {
        case COMPLETED:
            completionStatusCircle.setStyle(COLOR_COMPLETED_STYLE);
            completionStatus.setText("Completed");
            break;
        case IN_PROGRESS:
            completionStatusCircle.setStyle(COLOR_IN_PROGRESS_STYLE);
            completionStatus.setText("In Progress");
            break;
        case NOT_STARTED:
            completionStatusCircle.setStyle(COLOR_NOT_STARTED_STYLE);
            completionStatus.setText("Not Started");
            break;
        default:
            throw new AssertionError("Unknown CompletionStringStatus for Commission " + commission);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommissionCard)) {
            return false;
        }

        // state check
        CommissionCard card = (CommissionCard) other;
        return id.getText().equals(card.id.getText())
                && commission.equals(card.commission);
    }
}
