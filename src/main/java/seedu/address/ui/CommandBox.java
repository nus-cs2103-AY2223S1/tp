package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final CommandHistory commandHistory;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        this(commandExecutor, new CommandHistory());
    }

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor} and {@code CommandHistory}.
     */
    public CommandBox(CommandExecutor commandExecutor, CommandHistory commandHistory) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        this.commandHistory = commandHistory;
        commandTextField.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode key = event.getCode();
        boolean commandChanged = false;
        switch (key) {
        case DOWN:
            commandHistory.nextCommand();
            commandChanged = true;
            break;
        case UP:
            commandHistory.previousCommand();
            commandChanged = true;
            break;
        case C:
            if (event.isShiftDown() && event.isControlDown()) {
                setCommandTextField("");
            }
            break;
        default:
        }
        if (commandChanged) {
            setCommandTextField(commandHistory.getCommand().orElse(""));
        }
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
            commandHistory.addCommand(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Changes the text currently in the command to the given String.
     * @param text the text to set the field to.
     */
    protected void setCommandTextField(String text) {
        commandTextField.setText(text);
        commandTextField.requestFocus();
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
     * A Class to store the Command History of a Command Box.
     */
    public static class CommandHistory {
        private final List<String> previousCommands;
        private int pointer;

        /**
         * Creates a new CommandHistory object with a new list of commands.
         */
        public CommandHistory() {
            this(new ArrayList<>());
        }

        /**
         * Creates a new CommandHistory object with a pre-existing list of commands.
         * @param previousCommands List of commands.
         */
        public CommandHistory(List<String> previousCommands) {
            this.previousCommands = previousCommands;
            setPointerToEnd();
        }

        /**
         * Add a new command to the history.
         * @param command to be added.
         */
        public void addCommand(String command) {
            previousCommands.add(command);
            setPointerToEnd();
        }

        public Optional<String> getCommand() {
            if (pointer <= -1) {
                return Optional.empty();
            }
            if (pointer == previousCommands.size()) {
                return Optional.empty();
            }
            return Optional.of(previousCommands.get(pointer));
        }

        /**
         * Move the pointer back 1 command in the history.
         */
        public void previousCommand() {
            pointer = (pointer - 1) < 0 ? pointer : (pointer - 1);
            System.out.println(pointer);
            System.out.println(previousCommands);
        }

        /**
         * Move the pointer forward 1 command in the history.
         */
        public void nextCommand() {
            pointer = (pointer + 1) > previousCommands.size() ? pointer : (pointer + 1);
            System.out.println(pointer);
        }

        private void setPointerToEnd() {
            pointer = previousCommands.size();
        }
    }

}
