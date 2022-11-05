package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.team.Link;


/**
 * A UI component that displays information of a {@code Link}.
 */
public class LinkCard extends UiPart<Region> {

    private static final String FXML = "LinkListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on TruthTable level 4</a>
     */

    public final Link link;

    @javafx.fxml.FXML
    private HBox cardPane;
    @javafx.fxml.FXML
    private Hyperlink linkName;
    @FXML
    private Label id;

    private ResultDisplay resultDisplay;

    /**
     * Creates a {@code LinkCode} with the given {@code Link} and index to display.
     */
    public LinkCard(Link link, int displayedIndex, ResultDisplay resultDisplay) {
        super(FXML);
        this.link = link;
        id.setText(displayedIndex + ". ");

        linkName.setText(link.getDisplayedName().toString());
        linkName.setOnAction(event -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(link.getUrl().toString());
            clipboard.setContent(content);
            linkName.setVisited(false);
            resultDisplay.setFeedbackToUser("Copied URL to clipboard: " + link.getUrl().toString());
        });
        linkName.setVisited(false);
        linkName.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkCard)) {
            return false;
        }

        // state check
        LinkCard card = (LinkCard) other;
        return id.getText().equals(card.id.getText())
                && link.equals(card.link);
    }
}
