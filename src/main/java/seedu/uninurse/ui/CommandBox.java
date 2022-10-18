package seedu.uninurse.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.uninurse.logic.commands.CommandResult;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final CommandHistoryList history;
    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.history = new CommandHistoryList();
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
            commandTextField.setText("");
            history.add(commandText);
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles the Up and Down button pressed event.
     */
    @FXML
    private void handleOnKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        switch (keyEvent.getCode()) {
        case UP:
            if (history.handleUpKey()) {
                commandTextField.setText(history.get());
            }
            break;
        case DOWN:
            if (history.handleDownKey()) {
                commandTextField.setText(history.get());
            }
            break;
        default:
            // Default onKeyPress event
            break;
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
         * @see seedu.uninurse.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * The command history list.
     */
    private static final class CommandHistoryList {
        private final int sizeLimit = 100;
        private final List<String> history;
        private int currentPointer;

        /**
         * Creates a {@code CommandHistoryList}.
         */
        public CommandHistoryList() {
            history = new ArrayList<String>();
            history.add("");
            currentPointer = 0;
        }

        /**
         * Adds a successful command to the history list, if the last command is a different one.
         */
        public void add(String command) {
            if (history.size() < 2 || !history.get(history.size() - 2).equals(command)) {
                // Update history only if the new command is different from the last one
                history.set(history.size() - 1, command);
                while (history.size() > sizeLimit) {
                    history.remove(0);
                }
                history.add("");
            }
            currentPointer = history.size() - 1;
        }

        /**
         * Handles the Up Key Button Pressed event.
         */
        public boolean handleUpKey() {
            if (currentPointer == 0) {
                return false;
            }
            currentPointer -= 1;
            return true;
        }

        /**
         * Handles the Down Key Button Pressed event.
         */
        public boolean handleDownKey() {
            if (currentPointer + 1 == history.size()) {
                return false;
            }
            currentPointer += 1;
            return true;
        }

        /**
         * Returns the command currently pointed to in the history list.
         */
        public String get() {
            return history.get(currentPointer);
        }
    }

}
