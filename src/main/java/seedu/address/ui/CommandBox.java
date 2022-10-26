package seedu.address.ui;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.history.CommandHistory;


/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    // Command History
    private CommandHistory commandHistoryStorage = new CommandHistory();

    // Autocomplete
    // Command list to check with.
    private final SortedSet<String> commandList;
    // Pop up list with suggestions.
    private final ContextMenu suggestionsList;
    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandList = new TreeSet<>(Arrays.asList("add ", "delete ", "ls -a", "ls -m", "ls -u", "ls -t ",
                "ls --module ", "ls -n ", "mark ", "unmark ", "find ", "edit ", "clear ", "exit", "help"));
        suggestionsList = new ContextMenu();
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
     * Handles the Enter button, Up button and Down button pressed events.
     */
    @FXML
    private void handleButtonPressed(KeyEvent event) {

        KeyCode keyPressed = event.getCode();
        switch (keyPressed) {

        case ENTER:
            String commandText = commandTextField.getText();
            if (commandText.equals("")) {
                return;
            }

            try {
                commandHistoryStorage.add(commandText);
                commandExecutor.execute(commandText);
                commandTextField.setText("");
            } catch (CommandException | ParseException e) {
                setStyleToIndicateCommandFailure();
            }
            break;
        case UP:
            if (suggestionsList.isShowing()) {
                suggestionsList.hide();
            }
            String prevCommand = commandHistoryStorage.up();
            if (prevCommand.equals("")) {
                return;
            }
            commandTextField.setText(prevCommand);
            commandTextField.positionCaret(commandTextField.getText().trim().length());
            break;
        case DOWN:
            if (suggestionsList.isShowing()) {
                suggestionsList.hide();
            }
            String nextCommand = commandHistoryStorage.down();
            if (nextCommand.equals("")) {
                commandTextField.setText("");
                return;
            }
            commandTextField.setText(nextCommand);
            commandTextField.positionCaret(commandTextField.getText().trim().length());
            break;
        default:
            return;
        }
    }

    /**
     * Populate the popup with the given search results.
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        for (String result : searchResult) {
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(actionEvent -> {
                commandTextField.setText(result);
                suggestionsList.hide();
                commandTextField.positionCaret(commandTextField.getText().trim().length());
            });
            menuItems.add(item);
        }
        suggestionsList.getItems().clear();
        suggestionsList.getItems().addAll(menuItems);
    }

    /**
     * Filters the results for the suggestions list.
     * @param currentText Current text in commandTextArea
     * @return results
     */
    private LinkedList<String> filterList(String currentText) {
        return commandList.stream()
                .filter(a -> a.startsWith(currentText.toLowerCase().trim()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Handles the autocomplete function by filtering the command list.
     */
    @FXML
    private void handleAutoComplete() {
        String currentText = commandTextField.getText();
        if (currentText.trim().equals("")) {
            suggestionsList.hide();
        } else {
            LinkedList<String> searchResult = filterList(currentText);
            // If current text matches the only result in the list, close list.
            if (searchResult.size() == 1 && searchResult.get(0).equalsIgnoreCase(currentText)) {
                suggestionsList.hide();
                // Else, show all results.
            } else if (searchResult.size() > 0) {
                populatePopup(searchResult);
                suggestionsList.hide();
                suggestionsList.show(commandTextField, Side.BOTTOM, 10 + currentText.length() * 10, -10);
            } else {
                suggestionsList.hide();
            }
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

}
