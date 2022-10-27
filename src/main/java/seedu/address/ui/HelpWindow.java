package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import seedu.address.logic.commands.ExitCommand;
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
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL
            + "example\n"  + "example\n"  + "example\n"  + "example\n"  + "example\n"  + "example\n";

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

    public void set() {
        helpMessage.setText(HELP_MESSAGE);
    }

    public void set(CommandResult.CommandType commandType) {
        switch (commandType) {
        case ADD:
            helpMessage.setText(AddCommand.HELP_MESSAGE);
            break;
//        case EDIT:
//            helpMessage.setText(EditCommand.HELP_MESSAGE);
//            break;
//        case DELETE:
//            helpMessage.setText(DeleteCommand.HELP_MESSAGE);
//            break;
//        case SORT:
//            helpMessage.setText(SortCommand.HELP_MESSAGE);
//            break;
//        case CLEAR:
//            helpMessage.setText(ClearCommand.HELP_MESSAGE);
//            break;
//        case FIND:
//            helpMessage.setText(FindCommand.HELP_MESSAGE);
//            break;
//        case LIST:
//            helpMessage.setText(ListCommand.HELP_MESSAGE);
//            break;
//        case EXIT:
//            helpMessage.setText(ExitCommand.HELP_MESSAGE);
//            break;
//        case HELP:
//            helpMessage.setText(HelpCommand.HELP_MESSAGE);
//            break;
//        case SHOW:
//            helpMessage.setText(ShowCommand.HELP_MESSAGE);
//            break;
//        case ASSIGN:
//            helpMessage.setText(AssignCommand.HELP_MESSAGE);
//            break;
//        case UNASSIGN:
//            helpMessage.setText(UnassignCommand.HELP_MESSAGE);
//            break;
//        case NOK:
//            helpMessage.setText(NextOfKinCommand.HELP_MESSAGE);
//            break;
        case OTHER:
        default:
            helpMessage.setText(HELP_MESSAGE);
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
