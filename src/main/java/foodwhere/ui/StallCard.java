package foodwhere.ui;

import java.util.Comparator;

import foodwhere.model.stall.Stall;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Stall}.
 * Will be updated to a Stall for later iterations.
 */
public class StallCard extends UiPart<Region> {

    private static final String FXML = "StallListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Stall stall;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private FlowPane details;

    /**
     * Creates a {@code StallCode} with the given {@code Stall} and index to display.
     */
    public StallCard(Stall stall, int displayedIndex) {
        super(FXML);
        this.stall = stall;
        id.setText(displayedIndex + ". ");
        name.setText(stall.getName().fullName);
        phone.setText(stall.getPhone().value);
        address.setText(stall.getAddress().value);
        stall.getDetails().stream()
                .sorted(Comparator.comparing(detail -> detail.detail))
                .forEach(detail -> details.getChildren().add(new Label(detail.detail)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StallCard)) {
            return false;
        }

        // state check
        StallCard card = (StallCard) other;
        return id.getText().equals(card.id.getText())
                && stall.equals(card.stall);
    }
}
