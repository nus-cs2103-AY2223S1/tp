package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class CountWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(CountWindow.class);
    private static final String FXML = "CountWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label countMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public CountWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new CountWindow.
     */
    public CountWindow(String countMessage) {
        this(new Stage());
        this.countMessage.setText(countMessage);
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing count page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the count window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the count window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the count window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the count to the user guide to the clipboard.
     */
    @FXML
    private void copyCount() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(countMessage.getText());
        clipboard.setContent(url);
    }
}
