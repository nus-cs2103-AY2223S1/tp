package soconnect.ui;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import soconnect.logic.Logic;
import soconnect.logic.autocomplete.Autocomplete;
import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    private final Autocomplete autocomplete;

    @FXML
    private TextField commandTextField;

    @FXML
    private ContextMenu autocompletePopup;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, Autocomplete autocompleteManager) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.autocomplete = autocompleteManager;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        // set up a listener for autocomplete feature.
        setAutocompleteListener();
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
     * Displays a list of autocomplete entries.
     * Solution below adapted from https://stackoverflow.com/questions/36861056/javafx-textfield-auto-suggestions.
     */
    private void setAutocompleteListener() {
        commandTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String commandText = commandTextField.getText();

                List<String> autocompleteEntries = autocomplete.getAutocompleteEntries(commandText);
                if (autocompleteEntries.isEmpty()) {
                    autocompletePopup.hide();
                } else {
                    populatePopup(autocompleteEntries);
                    if (!autocompletePopup.isShowing()) {
                        autocompletePopup.show(commandTextField, Side.BOTTOM, 0, 0);
                    }
                }
            }
        });
    }

    /**
     * Generates a list of autocomplete entries in the {@code contextMenu}.
     * Solution below adapted from https://stackoverflow.com/questions/36861056/javafx-textfield-auto-suggestions.
     *
     * @param autocompleteEntries The list of matching strings.
     */
    private void populatePopup(List<String> autocompleteEntries) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        for (String autocompleteEntry : autocompleteEntries) {
            System.out.println("entry: " + autocompleteEntry);
            Label entryLabel = new Label(autocompleteEntry);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            // Whenever an item is selected, set text field to the selected text, execute the command and close pop up.
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    commandTextField.setText(autocompleteEntry);
                    handleCommandEntered();
                    autocompletePopup.hide();
                }
            });
            menuItems.add(item);
        }

        // Update the autocomplete pop up.
        autocompletePopup.getItems().clear();
        autocompletePopup.getItems().addAll(menuItems);
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
