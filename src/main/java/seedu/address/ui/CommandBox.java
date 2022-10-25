package seedu.address.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    // command log navigation
    private final List<String> commandLog;
    private int commandLogPointer;
    private String currentText;

    // autocomplete
    // suggestions for address book commands (excludes task related commands)
    private final SortedSet<String> suggestionsAb;
    // suggestions for task related commands
    private final SortedSet<String> suggestionsTasks;
    // pop up used to select a suggestion
    private final ContextMenu suggestionsPopup;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandLog = new ArrayList<>();
        suggestionsPopup = new ContextMenu();
        suggestionsAb = new TreeSet<>(
            Arrays.asList("add", "clear", "delete", "edit", "exit", "find", "help", "list", "task"));
        suggestionsTasks = new TreeSet<>(
            Arrays.asList("add", "assign", "deadline", "delete", "edit", "list", "mark", "unmark"));

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        addEvents();
    }

    /**
     * Sets focus on the CommandTextField.
     */
    public void focus() {
        commandTextField.requestFocus();
    }


    /**
     * Add events to commandTextField.
     */
    private void addEvents() {
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, this::handleNavigationKeysPressed);
        // prevents the suggestions popup from disappearing when user presses tab.
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB) {
                // Allow Tab key to shift focus to other GUI elements
                if (suggestionsPopup.isShowing()) {
                    suggestionsPopup.hide();
                }
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
     * If no more previous command and UP key is pressed, nothing happens.
     * If current text matches the latest command and DOWN key is pressed, nothing happens.
     */
    private void handleNavigationKeysPressed(KeyEvent event) {
        // TODO: Update this comment
        if (suggestionsPopup.isShowing()) {
            suggestionsPopup.hide();
            return;
        }

        // Guard: If there are no commands, nothing happens
        if (commandLog.size() == 0) {
            return;
        }

        if (event.getCode() == KeyCode.UP) {
            event.consume();

            // Guard: If the text field shows the earliest command, nothing changes
            if (commandLogPointer == 0) {
                return;
            }

            // Text in the commandTextField should be saved, so the user does not need to type it in again
            if (commandLogPointer == commandLog.size()) {
                currentText = commandTextField.getText();
            }

            commandTextField.setText(commandLog.get(--commandLogPointer));
        } else if (event.getCode() == KeyCode.DOWN) {
            event.consume();
            commandLogPointer += 1;

            // If text field shows the latest command, nothing changes
            if (commandLogPointer >= commandLog.size()) {
                commandLogPointer = commandLog.size();
                commandTextField.setText(currentText == null ? "" : currentText);
                return;
            }

            commandTextField.setText(commandLog.get(commandLogPointer));
        } else {
            // do not do anything if any other key is pressed
            return;
        }

        commandTextField.positionCaret(commandTextField.getText().length());
    }

    // Detects text changes in commandTextField to update suggestions popup
    @FXML
    private void handleTextChanged() {
        String currentText = commandTextField.getText();

        // Guard: Stop if no text in command text field
        if (currentText.isEmpty()) {
            suggestionsPopup.hide();
            return;
        }

        // "/" requests focus on the CommandTextField. Since it is an invalid starting command text, we can clear it.
        if (commandTextField.getText().length() == 1 && commandTextField.getText().equals("/")) {
            commandTextField.clear();
        }

        // Guard: If suggestions do not match text in command text field, stop
        if (suggestionsAb.size() == 0 || suggestionsTasks.size() == 0) {
            return;
        }

        List<String> searchResult;

        // Populate suggestions popup with task related commands
        if (currentText.startsWith(TaskCommand.COMMAND_WORD + " ")
            || currentText.startsWith(TaskCommand.COMMAND_WORD_ALIAS + " ")) {
            String resultFrom = currentText.substring(currentText.indexOf(" ")).stripLeading();
            searchResult = new LinkedList<>(suggestionsTasks
                .subSet(resultFrom, resultFrom + Character.MAX_VALUE));
            // Guard: If the text in command text field matches the only suggestion, hide the popup
            if (searchResult.size() == 1 && resultFrom.equalsIgnoreCase(searchResult.get(0))) {
                return;
            }
        } else {
            searchResult = new LinkedList<>(suggestionsAb
                .subSet(currentText, currentText + Character.MAX_VALUE));
            if (searchResult.size() == 1 && currentText.equalsIgnoreCase(searchResult.get(0))) {
                return;
            }
        }

        populatePopup(searchResult);
        suggestionsPopup.show(
            getRoot(), Side.BOTTOM, 14 + currentText.length() * 7, -8);
    }

    /**
     * Populate the suggestions popups with the given search results.
     * @param searchResult set of matching strings
     */
    private void populatePopup(List<String> searchResult) {
        String currentText = commandTextField.getText();
        List<CustomMenuItem> menuItems = new LinkedList<>();
        for (final String result : searchResult) {
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(actionEvent -> {
                // Checks if user is typing a task command
                if (currentText.startsWith(TaskCommand.COMMAND_WORD + " ")
                    || currentText.startsWith(TaskCommand.COMMAND_WORD_ALIAS + " ")) {
                    // Keep the front part (i.e. 'task or 't') the same
                    commandTextField.setText(currentText.split(" ")[0] + " " + result);
                } else {
                    // Autocomplete the user's current command
                    commandTextField.setText(result);
                }
                commandTextField.positionCaret(currentText.length());
                // Hide the popup once the autocomplete happens
                suggestionsPopup.hide();
            });
            menuItems.add(item);
        }
        suggestionsPopup.getItems().clear();
        suggestionsPopup.getItems().addAll(menuItems);
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
