package seedu.workbook.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Region;
import seedu.workbook.ui.util.HelpUtil;

/**
 * An UI component that displays the {@code URL} of the {@code User Guide}.
 */
public class UrlCard extends UiPart<Region> {

    private static final String FXML = "UrlCard.fxml";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private Label urlMessage;

    @FXML
    private Button copyButton;


    /**
     * Creates a {@code URLCard} with the given {@code URL} and message to display.
     */
    public UrlCard() {
        super(FXML);

        urlMessage.setText(HelpUtil.getUrlMessage());
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(HelpUtil.getUserGuideUrl());
        clipboard.setContent(url);
    }

}
