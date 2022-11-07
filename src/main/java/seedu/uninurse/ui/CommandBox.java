package seedu.uninurse.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.uninurse.commons.core.Config;
import seedu.uninurse.logic.commands.CommandResult;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {
    private static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final CommandHistoryList history;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a CommandBox with the given CommandExecutor.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.history = new CommandHistoryList();
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        this.commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
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
        Optional<String> text;
        switch (keyEvent.getCode()) {
        case UP:
            text = history.handleUpKey();
            break;
        case DOWN:
            text = history.handleDownKey();
            break;
        default:
            text = Optional.empty();
            break;
        }
        if (text.isPresent()) {
            commandTextField.setText(text.get());
            commandTextField.positionCaret(text.get().length());
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
        private final List<String> history;
        private int currentPointer;

        /**
         * Creates a CommandHistoryList.
         */
        private CommandHistoryList() {
            this.history = new ArrayList<>();
            this.history.add("");
            this.currentPointer = 0;
        }

        /**
         * Adds a successful command to the history list, if the last command is a different one.
         */
        private void add(String command) {
            if (history.size() < 2 || !history.get(history.size() - 2).equals(command)) {
                // Update history only if the new command is different from the last one
                history.set(history.size() - 1, command);
                while (history.size() > Config.HISTORY_SIZE_LIMIT) {
                    history.remove(0);
                }
                history.add("");
            }
            currentPointer = history.size() - 1;
            assert 1 <= history.size() && history.size() <= Config.HISTORY_SIZE_LIMIT + 1;
        }

        /**
         * Handles the Up Key Button Pressed event.
         */
        private Optional<String> handleUpKey() {
            assert 0 <= currentPointer && currentPointer <= history.size() - 1;
            if (currentPointer == 0) {
                return Optional.empty();
            }
            currentPointer--;
            return Optional.of(get());
        }

        /**
         * Handles the Down Key Button Pressed event.
         */
        private Optional<String> handleDownKey() {
            assert 0 <= currentPointer && currentPointer <= history.size() - 1;
            if (currentPointer + 1 == history.size()) {
                return Optional.empty();
            }
            currentPointer++;
            return Optional.of(get());
        }

        /**
         * Returns the command currently pointed to in the history list.
         */
        private String get() {
            assert 0 <= currentPointer && currentPointer <= history.size() - 1;
            return history.get(currentPointer);
        }
    }
}
