package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddInternshipCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteInternshipCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditInternshipCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindInternshipCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.ListInternshipCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.SortInternshipCommand;
import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.commands.UnlinkCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-f11-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide for full details: " + USERGUIDE_URL
            + "\nA summary of commands is provided below for convenience.";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private static final String COMMAND_SUMMARY = "SUMMARY OF COMMANDS:\n\n"
            + HelpCommand.COMMAND_WORD + ": Opens this help window.\n\n"
            + AddPersonCommand.MESSAGE_USAGE + "\n\n"
            + AddInternshipCommand.MESSAGE_USAGE + "\n\n"
            + ListPersonCommand.COMMAND_WORD + ": Lists all persons.\n\n"
            + ListInternshipCommand.COMMAND_WORD + ": Lists all internships.\n\n"
            + EditPersonCommand.MESSAGE_USAGE + "\n\n"
            + EditInternshipCommand.MESSAGE_USAGE + "\n\n"
            + LinkCommand.MESSAGE_USAGE + "\n\n"
            + UnlinkCommand.MESSAGE_USAGE + "\n\n"
            + FindPersonCommand.MESSAGE_USAGE + "\n\n"
            + FindInternshipCommand.MESSAGE_USAGE + "\n\n"
            + DeletePersonCommand.MESSAGE_USAGE + "\n\n"
            + DeleteInternshipCommand.MESSAGE_USAGE + "\n\n"
            + SortPersonCommand.MESSAGE_USAGE + "\n\n"
            + SortInternshipCommand.MESSAGE_USAGE + "\n\n"
            + ClearCommand.COMMAND_WORD + ": Clears all entries.\n\n"
            + ExitCommand.COMMAND_WORD + ": Exits the program.";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TextArea resultDisplay;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);

        resultDisplay.setText(COMMAND_SUMMARY);
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
