package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, ResultDisplay resultDisplay, Logic logic) {
        super(FXML);

        setupListener(resultDisplay);
        setupCommandHistoryNavigation(logic, resultDisplay);

        this.commandExecutor = commandExecutor;

    }

    public TextField getCommandTextField() {
        return commandTextField;
    }

    /**
     * Setup down and up arrow key to show previous and next command in commandTextField
     * @param logic gets the previous/next command in CommandHistory
     * @param resultDisplay is cleared when the next command string is empty
     */
    private void setupCommandHistoryNavigation(Logic logic, ResultDisplay resultDisplay) {
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
            case UP:
                String prevCommand = logic.getPrevInCommandHistory();
                if (prevCommand != "") {
                    commandTextField.setText(prevCommand);
                }
                event.consume();
                break;
            case DOWN:
                String nextCommand = logic.getNextInCommandHistory();
                commandTextField.setText(nextCommand);
                if (nextCommand.equals("")) {
                    resultDisplay.setFeedbackToUser("");
                }
                event.consume();
                break;
            default:
                break;
            }
        });
    }

    /**
     * Add listener to commandTextField to set text style to default when typing
     * and to display the command's message usage to the ResultDisplay when
     * a valid command word is typed.
     * @param resultDisplay displays the command's message usage
     */
    private void setupListener(ResultDisplay resultDisplay) {
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        // displays command parameters in ResultDisplay when a COMMAND_WORD is typed.
        // clears ResultDisplay when a single character is in the CommandBox
        commandTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            setStyleToDefault();
            if (newValue.length() == 1) {
                resultDisplay.setFeedbackToUser("");
            }
            String trimmedText = newValue.trim();
            String commandWord = trimmedText.contains(" ") ? trimmedText.split(" ")[0] : trimmedText;
            switch (commandWord) {
            case AddCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(AddCommand.MESSAGE_USAGE);
                break;
            case EditCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(EditCommand.MESSAGE_USAGE);
                break;
            case DeleteCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(DeleteCommand.MESSAGE_USAGE);
                break;
            case FindCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(FindCommand.MESSAGE_USAGE);
                break;
            case ListCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(ListCommand.MESSAGE_USAGE);
                break;
            case ClearCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(ClearCommand.MESSAGE_USAGE);
                break;
            case SortCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(SortCommand.MESSAGE_USAGE);
                break;
            case AddAppointmentCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(AddAppointmentCommand.MESSAGE_USAGE);
                break;
            case EditAppointmentCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(EditAppointmentCommand.MESSAGE_USAGE);
                break;
            case DeleteAppointmentCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(DeleteAppointmentCommand.MESSAGE_USAGE);
                break;
            case ExitCommand.COMMAND_WORD:
                resultDisplay.setFeedbackToUser(ExitCommand.MESSAGE_USAGE);
                break;
            default:
                break;
            }
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
