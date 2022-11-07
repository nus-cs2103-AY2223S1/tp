package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
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
     * Fills the command box with a command if it is currently empty.
     * @param command The command to set to.
     */
    private void setCommand(String command) {
        if (commandTextField.getText().isEmpty()) {
            commandTextField.setText(command);
        }
    }

    /**
     * Constructs and returns the {@code CommandBox}'s corresponding
     * {@code CommandSetter}.
     * @return A {@code CommandSetter} that can set this {@code CommandBox}'s command.
     */
    public CommandSetter getCommandSetter() {
        return new CommandSetter(this);
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
     * Represents a class which can only be used to set the content of the
     * {@code CommandBox} if it is empty.
     */
    public static class CommandSetter {
        private final CommandBox commandBox;

        /**
         * Creates a {@code CommandSetter} that can modify the written command.
         * @param commandBox The parent {@code CommandBox}.
         */
        private CommandSetter(CommandBox commandBox) {
            this.commandBox = commandBox;
        }

        /**
         * Sets the command if it is empty.
         * @param command The command to set.
         */
        public void setCommand(String command) {
            commandBox.setCommand(command);
        }
    }
}
