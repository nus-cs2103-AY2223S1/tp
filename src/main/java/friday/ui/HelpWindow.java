package friday.ui;

import java.util.logging.Logger;

import friday.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-w15-4.github.io/tp/UserGuide.html";
    public static final String HELP_CONTENT = "Here are all the commands in Friday:\n"
            + "add - to add a student\n"
            + "delete - to delete a student\n"
            + "edit - to edit a specific student's details\n"
            + "list - to list all students\n"
            + "sort - sort students based on the given criteria\n"
            + "find - to find students related to a keyword\n"
            + "grade - grade a specific student\n"
            + "remark - to add a remark to a specific student\n"
            + "mark - to mark the mastery check of a specific student\n"
            + "unmark - to unmark the mastery check of a specific student\n"
            + "alias - to add alias for a command to FRIDAY\n"
            + "unalias - to delete an alias for a command in FRIDAY\n"
            + "aliaslist - to display all alias in FRIDAY\n"
            + "clear - to clear all data in FRIDAY\n"
            + "guide - display a link to the User Guide\n"
            + "help - to display a help message\n"
            + "For more details, refer to the user guide: ";
    public static final String HELP_MESSAGE = HELP_CONTENT + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
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
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
