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
    public static final String QUICKSTART_TITLE = "Quick Start:";
    public static final String USER_QUICKSTART = "Type the command in the “COMMAND INPUT” box and press “ENTER” to "
            + "execute the command\n"
            + "Some example commands you can try:\n"
            + "add -n Tutorial 3 -m CS2103T -d 2022-09-16 : "
            + "Adds a task called Tutorial 3 for the module CS2103T with the deadline 2022-09-16 into the task list.\n"
            + "mark 1 : Marks the first task in the list as complete.\n"
            + "ls --module CS2103T : Lists all tasks associated with the module CS2103T.\n"
            + "delete 2 : Deletes the second task in the list.\n"
            + "edit 3 -n Assignment 2 : Changes the name of the third task in the list to Assignment 2.\n"
            + "find tutorial : "
            + "Finds anything with the keyword 'tutorial' (not case-sensitive or strictly matched words)";
    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-f12-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "For additional help, refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;
    @FXML
    private Label quickStartHeader;
    @FXML
    private Label quickStartMessage;
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
        quickStartMessage.setText(USER_QUICKSTART);
        quickStartHeader.setText(QUICKSTART_TITLE);
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
