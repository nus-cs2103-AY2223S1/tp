package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import seedu.address.model.commission.Commission;

/**
 * An UI component that displays information of a {@code Commission}.
 */
public class CommissionCard extends UiPart<Region> {

    private static final String FXML = "CommissionListCard.fxml";
    private static final Color COMPLETED_COLOR = Color.rgb(50, 174, 70);
    private static final Color NOT_COMPLETED_COLOR = Color.rgb(84, 141, 225);
    private static final Image CALENDAR_ICON = new Image("/images/calendar light.png");
    private static final Image MONEY_ICON = new Image("/images/money bag medium.png");

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
    @FXML
    private ImageView deadlineIcon;
    @FXML
    private Label deadline;
    @FXML
    private ImageView feeIcon;
    @FXML
    private Label fee;
    @FXML
    private Circle completionStatusCircle;
    @FXML
    private Label completionStatus;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code CustomerCode} with the given {@code Customer} and index to display.
     */
    public CommissionCard(Commission commission, int displayedIndex) {
        super(FXML);
        this.commission = commission;
        deadlineIcon.setImage(CALENDAR_ICON);
        feeIcon.setImage(MONEY_ICON);
        id.setText(displayedIndex + ". ");
        title.setText(commission.getTitle().title);
        deadline.setText(commission.getDeadline().deadline.toString());
        fee.setText("$" + String.format("%.2f", commission.getFee().fee));

        if (commission.getCompletionStatus().isCompleted) {
            completionStatusCircle.setFill(COMPLETED_COLOR);
            completionStatus.setText("Completed");
        } else {
            completionStatusCircle.setFill(NOT_COMPLETED_COLOR);
            completionStatus.setText("Not Completed");
        }

        commission.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label tagLabel = new Label(tag.tagName);
                    tags.getChildren().add(tagLabel);
                });
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
