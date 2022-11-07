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
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-w09-4.github.io/tp/UserGuide.html";
    public static final String BUFFER_LINE = "-------------------------------------------------------------------\n";
    //move copyButton in HelpWindow FXML if changed
    public static final String BASIC_COMMANDS = "1. To add a new student:\n"
            + "> add n/NAME p/PHONE_NUMBER lp/LESSON_PLAN\n"
            + "2. To find a student in your address book:\n" + "> find NAME\n"
            + "3. To view all information of an existing student:\n" + "> view NAME\n"
            + "4. To edit a students details:\n" + "> edit [n/ p/ lp/]NEW_FIELD\n"
            + "> edit [h/ a/ g/ s/]INDEX NEW_FIELD \n"
            + "5. To delete a student from your address book:\n" + "> delete INDEX\n"
            + "6. To remove a students details:\n" + "> view NAME\n"
            + "> remove [s/ h/ g/ a/]INDEX\n"
            + "7. To mark a students details:\n" + "> view NAME\n"
            + "> mark [h/ a/]INDEX\n"
            + "8. To unmark a students details:\n" + "> view NAME\n"
            + "> unmark [h/ a/]INDEX\n"
            + "9. To show lessons in the day:\n" + "> show DAY\n";

    public static final String HELP_MESSAGE = BUFFER_LINE + BASIC_COMMANDS + BUFFER_LINE
            + "For other queries, you can refer to the user guide: \n" + USERGUIDE_URL;
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
        helpMessage.setStyle(
                "-fx-font-family: \"Figtree\"; "
                        + "-fx-font-size: 12pt; "
                        + "-fx-text-fill: white;"
                        + "-fx-font-weight: 400;"
        );
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
