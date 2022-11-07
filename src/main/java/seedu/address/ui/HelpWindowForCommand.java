package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AppendCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CloneCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.ToggleListModeCommand;
import seedu.address.logic.commands.UnappendCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.commands.ViewCommand;

/**
 * Controller for a help page
 */
public class HelpWindowForCommand extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(HelpWindowForCommand.class);
    private static final String FXML = "HelpWindowForCommand.fxml";

    private final String lightTheme = getClass().getResource("/view/HelpWindowLight.css").toExternalForm();
    private final String darkTheme = getClass().getResource("/view/HelpWindowDark.css").toExternalForm();

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindowForCommand(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindowForCommand() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException
     *                               <ul>
     *                               <li>if this method is called on a thread other
     *                               than the JavaFX Application Thread.</li>
     *                               <li>if this method is called during animation
     *                               or layout processing.</li>
     *                               <li>if this method is called on the primary
     *                               stage.</li>
     *                               <li>if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing help page about a command.");
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
     * Set theme of help window to the light theme.
     */
    public void setLightTheme() {
        getRoot().getScene().getStylesheets().add(lightTheme);
        getRoot().getScene().getStylesheets().remove(darkTheme);
    }

    /**
     * Set theme of help window to the dark theme.
     */
    public void setDarkTheme() {
        getRoot().getScene().getStylesheets().add(darkTheme);
        getRoot().getScene().getStylesheets().remove(lightTheme);
    }

    /**
     * Set text of help window depending on the commandString.
     */
    public void setTextString(String commandString) {
        String helpMessageString = "";
        switch (commandString) {
        case AddCommand.COMMAND_WORD:
            helpMessageString = AddCommand.MESSAGE_USAGE;
            break;

        case EditCommand.COMMAND_WORD:
            helpMessageString = EditCommand.MESSAGE_USAGE;
            break;

        case DeleteCommand.COMMAND_WORD:
            helpMessageString = DeleteCommand.MESSAGE_USAGE;
            break;

        case ClearCommand.COMMAND_WORD:
            helpMessageString = ClearCommand.MESSAGE_USAGE;
            break;

        case ListCommand.COMMAND_WORD:
            helpMessageString = ListCommand.MESSAGE_USAGE;
            break;

        case ViewCommand.COMMAND_WORD:
            helpMessageString = ViewCommand.MESSAGE_USAGE;
            break;

        case CloneCommand.COMMAND_WORD:
            helpMessageString = CloneCommand.MESSAGE_USAGE;
            break;

        case UndoCommand.COMMAND_WORD:
            helpMessageString = UndoCommand.MESSAGE_USAGE;
            break;

        case MarkCommand.COMMAND_WORD:
            helpMessageString = MarkCommand.MESSAGE_USAGE;
            break;

        case UnmarkCommand.COMMAND_WORD:
            helpMessageString = UnmarkCommand.MESSAGE_USAGE;
            break;

        case ExitCommand.COMMAND_WORD:
            helpMessageString = ExitCommand.MESSAGE_USAGE;
            break;

        case HelpCommand.COMMAND_WORD:
            helpMessageString = HelpCommand.MESSAGE_USAGE;
            break;

        case ThemeCommand.COMMAND_WORD:
            helpMessageString = ThemeCommand.MESSAGE_USAGE;
            break;

        case AppendCommand.COMMAND_WORD:
            helpMessageString = AppendCommand.MESSAGE_USAGE;
            break;

        case UnappendCommand.COMMAND_WORD:
            helpMessageString = UnappendCommand.MESSAGE_USAGE;
            break;

        case ToggleListModeCommand.COMMAND_WORD:
            helpMessageString = ToggleListModeCommand.MESSAGE_USAGE;
            break;

        default:
        }
        helpMessage.setText(helpMessageString);
    }

}
