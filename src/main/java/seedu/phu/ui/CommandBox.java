package seedu.phu.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.phu.logic.commands.CommandResult;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final History inputHistory;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        inputHistory = new History();
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
            inputHistory.addInput(commandText);
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles UP and DOWN key pressed event.
     */
    @FXML
    private void handleKeyClick(KeyEvent keyEvent) {
        KeyCode clickedKey = keyEvent.getCode();

        if (clickedKey.equals(KeyCode.UP)) {
            keyEvent.consume();
            fetchPreviousInput();
        } else if (clickedKey.equals(KeyCode.DOWN)) {
            keyEvent.consume();
            fetchNextInput();
        }
    }

    /**
     * Replace current text with previous input, if exists.
     */
    private void fetchPreviousInput() {
        if (!inputHistory.isPreviousInputExists()) {
            return;
        }

        String previousInput = inputHistory.getPreviousInput();
        commandTextField.setText(previousInput);
    }

    /**
     * Replace current text with next input, if exists.
     */
    private void fetchNextInput() {
        if (!inputHistory.isNextInputExists()) {
            return;
        }

        String nextInput = inputHistory.getNextInput();
        commandTextField.setText(nextInput);
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
         * @see seedu.phu.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * Class which represents the input history.
     */
    public class History {
        private List<String> history;
        private int currentOneBasedIndex;

        /**
         * Creates a new instance of History.
         */
        public History() {
            history = new ArrayList<>();
            history.add("");
            currentOneBasedIndex = 1;
        }

        /**
         * Check if there is previous input in history.
         *
         * @return true if there is previous input in history; false otherwise.
         */
        public boolean isPreviousInputExists() {
            return currentOneBasedIndex > 1;
        }

        /**
         * Returns the previous input in history.
         *
         * @return the previous input.
         */
        public String getPreviousInput() {
            // only use this method when currentOneBasedIndex is larger than 1
            assert currentOneBasedIndex > 0;

            currentOneBasedIndex--;
            return history.get(currentOneBasedIndex);
        }

        /**
         * Check if there is next input in history.
         *
         * @return true if there is next input in history; false otherwise.
         */
        public boolean isNextInputExists() {
            return currentOneBasedIndex < history.size() - 1;
        }

        /**
         * Returns the next input in history.
         *
         * @return the next input.
         */
        public String getNextInput() {
            // only use this method when currentOneBasedIndex is less than size minus 1
            assert currentOneBasedIndex < history.size() - 1;

            currentOneBasedIndex++;
            return history.get(currentOneBasedIndex);
        }

        /**
         * Add a new input to history, only if it's different with the last input in current history.
         */
        public void addInput(String input) {
            // similar to terminal, only add input to history if it is different with the last one
            if (!input.equals(history.get(history.size() - 1))) {
                history.add(input);
            }
            currentOneBasedIndex = history.size();
        }
    }

}
