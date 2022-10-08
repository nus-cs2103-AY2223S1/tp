package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.poc.Poc;

/**
 * A UI component that displays information of a {@code Poc}.
 */
public class PocCard extends UiPart<Region> {

    private static final String FXML = "PocListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on JeeqTracker level 4</a>
     */

    public final Poc poc;

    @FXML
    private HBox pocCardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PocCode} with the given {@code Poc} and index to display
     */
    public PocCard(Poc poc, int displayedIndex) {
        super(FXML);
        this.poc = poc;
        id.setText(displayedIndex + ". ");
        name.setText(poc.getName().toString());
        email.setText(poc.getEmail().toString());
        phone.setText(poc.getPhone().toString());
        poc.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof PocCard)) {
            return false;
        }

        // state check
        PocCard card = (PocCard) other;
        return id.getText().equals(card.id.getText())
                && poc.equals(card.poc);
    }
}
