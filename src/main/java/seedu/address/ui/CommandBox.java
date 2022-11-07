package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
    private static int pos = 0;
    private static String currentInput = "";
    private static final ArrayList<String> previousCommands = new ArrayList<>();

    private final CommandExecutor commandExecutor;
    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandTextField.textProperty().addListener(((obs, oldValue, newValue) -> {
            // calls #setStyleToDefault() whenever there is a change to the text of the command box.
            setStyleToDefault();
            if (pos == previousCommands.size()) {
                currentInput = newValue;
            }
        }));
        commandTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.UP) && (pos - 1 < previousCommands.size()) && pos - 1 >= 0) {
                pos -= 1;
                commandTextField.setText(previousCommands.get(pos));
                commandTextField.positionCaret(Integer.MAX_VALUE);
            } else if (event.getCode().equals(KeyCode.DOWN) && (pos + 1 < previousCommands.size())) {
                pos += 1;
                commandTextField.setText(previousCommands.get(pos));
                commandTextField.positionCaret(Integer.MAX_VALUE);
            } else if (event.getCode().equals(KeyCode.DOWN) && (pos + 1 <= previousCommands.size())) {
                pos = previousCommands.size();
                commandTextField.setText(currentInput);
                commandTextField.positionCaret(Integer.MAX_VALUE);
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
            previousCommands.add(commandText);
            pos = previousCommands.size();
            currentInput = "";
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
