package hobbylist.ui;

import java.util.Comparator;

import hobbylist.model.activity.Activity;
import hobbylist.model.tag.Tag;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

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
    private Label tags;

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
                .forEach(tag -> tags.setText(tag.tagName));
        activity.getTags().stream()
                .forEach(tag -> tags.setBackground(new Background(
                        new BackgroundFill(Color.web(intToHexColor(tag)), null, null))));
    }

    private String intToHexColor(Tag tag) {
        return String.format("#%06X", (0xFFFFFF & tag.hashCode() ));
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
