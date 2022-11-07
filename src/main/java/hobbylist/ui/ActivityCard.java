package hobbylist.ui;

import java.util.Comparator;

import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Status;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * An UI component that displays information of a {@code Activity}.
 */
public class ActivityCard extends UiPart<Region> {

    private static final String FXML = "ActivityListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Activity activity;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane date;
    @FXML
    private Label status;
    @FXML
    private Label review;

    /**
     * Creates a {@code ActivityCard} with the given {@code Activity} and index to display.
     */
    public ActivityCard(Activity activity, int displayedIndex) {
        super(FXML);
        this.activity = activity;
        id.setText(displayedIndex + ". ");
        name.setText(activity.getName().fullName);
        description.setText(activity.getDescription().value);
        activity.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        activity.getDate().stream().forEach(d -> date.getChildren().add(new Label(d.toViewString())));

        tags.getChildren().forEach(child -> {
            Label tagLabel = (Label) child;
            tagLabel.setStyle("-fx-background-color: " + intToHexColor(tagLabel.getText()));
        });

        setStatusLabel(status, activity.getStatus());
        tags.getChildren().add(status);

        if (activity.getRating() != 0) {
            Label ratingLabel = new Label(
                    new String(new char[activity.getRating()]).replace("\0", "★"));
            ratingLabel.setStyle("-fx-background-color: " + String.format("#%06X", (0x2e2e2e)));
            tags.getChildren().add(ratingLabel);
        }

        if (activity.getReview().isPresent()) {
            review.setText("Review: " + activity.getReview().get().value);
        } else {
            VBox parent = (VBox) review.getParent();
            parent.getChildren().remove(review);
        }
    }

    private String intToHexColor(String tag) {
        return String.format("#%06X", (0xFFFFFF & tag.hashCode()));
    }

    public static void setStatusLabel(Label label, Status status) {
        label.setText("Status: " + status.toString());

        switch (status.toString()) {
        case Status.STATUS_UPCOMING:
            label.setStyle("-fx-background-color:cornflowerblue");
            break;

        case Status.STATUS_ONGOING:
            label.setStyle("-fx-background-color:green");
            break;

        case Status.STATUS_COMPLETED:
            label.setStyle("-fx-background-color:red");
            break;

        default:
            break;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ActivityCard)) {
            return false;
        }

        // state check
        ActivityCard card = (ActivityCard) other;
        return id.getText().equals(card.id.getText())
                && activity.equals(card.activity);
    }
}
