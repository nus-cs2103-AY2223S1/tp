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

    // suggestions

    // suggestions for address book commands (excludes task related commands)
    private final SortedSet<String> suggestionsAb;
    // suggestions for task related commands
    private final SortedSet<String> suggestionsTasks;
    // pop up used to select a suggestion
    private ContextMenu suggestionsPopup;

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
        suggestionsAb = new TreeSet<>(Arrays.asList("add", "delete", "edit", "exit"));
        suggestionsTasks = new TreeSet<>();

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

    // suggestions: https://gist.github.com/floralvikings/10290131
    // TODO: Update JavaDocs code
    @FXML
    private void handleTextChanged() {
        commandTextField.textProperty().addListener((observable, oldText, newText) -> {
            if (commandTextField.getText().isEmpty()) {
                suggestionsPopup.hide();
                return;
            }

            LinkedList<String> searchResult = new LinkedList<>(
                suggestionsAb.subSet(commandTextField.getText(), commandTextField.getText() + Character.MAX_VALUE));

            if (suggestionsAb.size() > 0) {
                populatePopup(searchResult);

                if (!suggestionsPopup.isShowing()) {
                    suggestionsPopup.show(getRoot(), Side.BOTTOM, 0, 0);
                } else {
                    suggestionsPopup.hide();
                }
            }
        });

        commandTextField.focusedProperty().addListener((observableValue, aBoolean, aBoolean2) ->
            suggestionsPopup.hide());
    }

    /**
     * Populate the suggestions popups with the given search results.
     * @param searchResult set of matching strings
     */
    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        for (final String result : searchResult) {
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(actionEvent -> {
                commandTextField.setText(result);
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
