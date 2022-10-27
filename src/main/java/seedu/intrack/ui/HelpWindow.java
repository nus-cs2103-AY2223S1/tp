package seedu.intrack.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.intrack.commons.core.LogsCenter;

import seedu.intrack.logic.commands.AddCommand;
import seedu.intrack.logic.commands.ClearCommand;
import seedu.intrack.logic.commands.DeleteCommand;
import seedu.intrack.logic.commands.EditCommand;
import seedu.intrack.logic.commands.ListCommand;
import seedu.intrack.logic.commands.SelectCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t11-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: ";
    public static final String HELP_COMMANDS = "Stuck? Here are a few commands that might help you out!\n"
            + "1. List out all your added internships: " + ListCommand.COMMAND_WORD
            + "\n\n2. Clear the default list of applications: " + ClearCommand.COMMAND_WORD
            + "\n\n3. Add a new application: " + AddCommand.COMMAND_WORD + "\n\n4. To edit an application: "
            + "\n4a. Select the desired entry: " + SelectCommand.COMMAND_WORD + "\n4b. Then edit said entry: "
            + EditCommand.COMMAND_WORD + "\n\n5. Delete an entry: " + DeleteCommand.COMMAND_WORD
            + "\n\nFor more commands, check out our user guide!";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label helpCommands;

    @FXML
    private Label helpLink;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        helpLink.setText(USERGUIDE_URL);
        helpCommands.setText(HELP_COMMANDS);
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
