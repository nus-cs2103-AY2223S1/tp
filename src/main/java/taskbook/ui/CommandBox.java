package taskbook.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import taskbook.logic.Logic;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.exceptions.ParseException;

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
    public CommandBox(CommandExecutor commandExecutor, CommandGetter previousCommand, CommandGetter nextCommand) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        setUpKeyPressedEvent(previousCommand, nextCommand);
    }

    /**
     * Updates the text field and caret position based on the given {@code command}.
     * Does nothing if command is null.
     */
    private void updateTextFieldWithHistory(String command) {
        if (command == null) {
            return;
        }

        commandTextField.setText(command);
        // Change caret / cursor position.
        commandTextField.positionCaret(command.length());
    }

    /**
     * Detects if the user clicks up and down arrow keys and handles them.
     */
    private void setUpKeyPressedEvent(CommandGetter previousCommand, CommandGetter nextCommand) {
        commandTextField.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            if (keyCode.equals(KeyCode.UP) || keyCode.equals(KeyCode.KP_UP)) {
                String command = previousCommand.get();
                updateTextFieldWithHistory(command);
            } else if (keyCode.equals(KeyCode.DOWN) || keyCode.equals(KeyCode.KP_DOWN)) {
                String command = nextCommand.get();
                updateTextFieldWithHistory(command);
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
         * @see Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * Represents a function for getting the command history.
     */
    @FunctionalInterface
    public interface CommandGetter {
        String get();
    }
}
