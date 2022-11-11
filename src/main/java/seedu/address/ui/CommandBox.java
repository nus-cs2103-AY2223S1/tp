package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private CommandRetriever commandRetriever;
    private int previousCommandsIndex;

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandRetriever = new CommandRetriever();
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
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
            commandRetriever.addCommand(commandText, commandTextField);
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles any key release.
     * UP arrow invokes {@link CommandRetriever#getPreviousCommand(TextField commandTextField)}.
     * DOWN arrow invokes {@link CommandRetriever#getNextCommand(TextField commandTextField)}.
     * Does nothing for other keys.
     *
     * @param e KeyEvent denoting the key that was released
     */
    @FXML
    private void handleKeyReleased(KeyEvent e) {
        KeyCode keyCode = e.getCode();
        if (keyCode.equals(KeyCode.UP)) {
            commandRetriever.getPreviousCommand(commandTextField);
        } else if (keyCode.equals(KeyCode.DOWN)) {
            commandRetriever.getNextCommand(commandTextField);
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

    /**
     * Contains all successfully executed commands from the last application start.
     * Keeps track of the currently displayed command's index in the history.
     */
    private static class CommandRetriever {
        private final ArrayList<String> commandHistory;
        private int index;
        private String currentCommand;

        public CommandRetriever() {
            commandHistory = new ArrayList<>();
            currentCommand = "";

            index = 0;
        }

        /**
         * Adds a successfully executed command into {@code commandRetriever}, and sets
         * {@code textField} to an empty string.
         *
         * @param command the user-inputted String of the successfully executed command.
         */
        public void addCommand(String command, TextField textField) {
            commandHistory.add(command);
            textField.setText("");

            index = commandHistory.size();
        }

        private boolean isCurrentCommand() {
            return index == commandHistory.size();
        }

        private boolean isMostRecentInCommandHistory() {
            return commandHistory.size() > 0 && index == commandHistory.size() - 1;
        }

        private boolean noPreviousCommand() {
            return index <= 0;
        }

        private boolean noNextCommand() {
            return index >= commandHistory.size();
        }

        private void setCurrentCommand(String command) {
            currentCommand = command;
        }

        private String getCurrentCommand() {
            return currentCommand;
        }

        /**
         * Sets the text in {@code textField} to the previous command, if a previous command exists.
         * If the current command is the most recent command, adds that to history.
         *
         * @param textField the TextField where the user inputs commands in.
         */
        public void getPreviousCommand(TextField textField) {
            if (noPreviousCommand()) {
                return;
            } else if (isCurrentCommand()) {
                setCurrentCommand(textField.getText());
            }
            textField.setText(commandHistory.get(--index));
        }

        /**
         * Sets the text in {@code textField} to the next command, if a next command exists.
         *
         * @param textField the TextField where the user inputs commands in.
         */
        public void getNextCommand(TextField textField) {
            if (noNextCommand()) {
                return;
            } else if (isMostRecentInCommandHistory()) {
                textField.setText(getCurrentCommand());
                index++;
            } else {
                textField.setText(commandHistory.get(++index));
            }
        }
    }
}
