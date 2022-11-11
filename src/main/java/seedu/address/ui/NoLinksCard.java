package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * A UI component that displays a message to indicate that there are no
 * {@code Link}s in the module.
 */
public class NoLinksCard extends UiPart<Text> {
    private static final String FXML = "NoLinksCard.fxml";
    private static final String MESSAGE_NO_LINKS = "You have not added any "
            + "links for this module.";

    @FXML
    private Label noLinkMessage;

    /**
     * Creates a {@code NoLinksCard} to display.
     */
    public NoLinksCard() {
        super(FXML);
        noLinkMessage.setText(MESSAGE_NO_LINKS);
    }
}
