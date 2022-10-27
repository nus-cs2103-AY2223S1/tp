package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.NextOfKinCommand;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UnassignCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private TextArea textArea;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        textArea.setText(HelpCommand.MESSAGE_USAGE);
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

    public void set() {
        textArea.setText(HelpCommand.MESSAGE_USAGE);
    }

    public void set(CommandResult.CommandType commandType) {
        assert(commandType != CommandResult.CommandType.EXIT
                || commandType != CommandResult.CommandType.HELP
                || commandType != CommandResult.CommandType.OTHER);
        switch (commandType) {
        case ADD:
            textArea.setText(AddCommand.MESSAGE_USAGE);
            break;
        case EDIT:
            textArea.setText(EditCommand.MESSAGE_USAGE);
            break;
        case DELETE:
            textArea.setText(DeleteCommand.MESSAGE_USAGE);
            break;
        case SORT:
            textArea.setText(SortCommand.MESSAGE_USAGE);
            break;
        case CLEAR:
            textArea.setText(ClearCommand.MESSAGE_USAGE);
            break;
        case FIND:
            textArea.setText(FindCommand.MESSAGE_USAGE);
            break;
        case LIST:
            textArea.setText(ListCommand.MESSAGE_USAGE);
            break;
        case SHOW:
            textArea.setText(ShowCommand.MESSAGE_USAGE);
            break;
        case ASSIGN:
            textArea.setText(AssignCommand.MESSAGE_USAGE);
            break;
        case UNASSIGN:
            textArea.setText(UnassignCommand.MESSAGE_USAGE);
            break;
        case NOK:
            textArea.setText(NextOfKinCommand.MESSAGE_USAGE);
            break;
        default:
            textArea.setText(HelpCommand.MESSAGE_USAGE);
        }
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
