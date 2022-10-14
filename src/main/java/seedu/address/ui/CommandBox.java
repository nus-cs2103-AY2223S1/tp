package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

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

    private final CommandExecutor commandExecutor;

    private final List<String> commandLog;
    private int commandLogPointer;
    private String currentText;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandLog = new ArrayList<>();
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        addNavigateCommandsEvent();
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
            commandLog.add(commandText);
            commandLogPointer = commandLog.size();

            commandTextField.setText("");
            commandExecutor.execute(commandText);
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Allows the user to navigate between previously entered commands with the UP and DOWN arrow keys.
     * This function adds an event filter that checks whenever the user presses the UP and DOWN keys.
     */
    private void addNavigateCommandsEvent() {
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, this::handleNavigationKeysPressed);
    }

    /**
     * If no more previous command and UP key is pressed, nothing happens.
     * If current text matches the latest command and DOWN key is pressed, nothing happens.
     */
    private void handleNavigationKeysPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            event.consume();
            // if text field shows the earliest command, nothing changes.
            if (commandLogPointer == 0) {
                return;
            }

            if (commandLogPointer == commandLog.size()) {
                currentText = commandTextField.getText();
            }

            commandLogPointer -= 1;
            commandTextField.setText(commandLog.get(commandLogPointer));

        } else if (event.getCode() == KeyCode.DOWN) {
            event.consume();
            commandLogPointer += 1;

            // if text field shows the latest command, nothing changes.
            if (commandLogPointer >= commandLog.size()) {
                commandLogPointer = commandLog.size();
                commandTextField.setText(currentText);
                return;
            }

            commandTextField.setText(commandLog.get(commandLogPointer));
        } else {
            // do not do anything if any other key is pressed.
            return;
        }

        commandTextField.positionCaret(commandTextField.getLength());
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
