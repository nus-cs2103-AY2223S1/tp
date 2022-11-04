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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
    private static final double LABEL_PREFERRED_HEIGHT = 20;

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

        commandTextField.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handleCommandEntered();
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
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Activates a listener to execute autocomplete related actions.
     */
    private void setAutocompleteListener() {
        commandTextField.setOnKeyPressed(e -> {
            // Change focus to the autocomplete popup
            if (e.getCode() == KeyCode.DOWN && autocompletePopup.isShowing()) {
                autocompletePopup.getSkin().getNode().lookup(".menu-item").requestFocus();
            }
        });

        commandTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                autocompleteAction();
            }
        });

        commandTextField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                autocompleteAction();
            }
        });
    }

    /**
     * Generates and displays the autocomplete pop up.
     * Solution below adapted from https://stackoverflow.com/questions/36861056/javafx-textfield-auto-suggestions.
     */
    private void autocompleteAction() {
        String commandText = commandTextField.getText().trim();
        List<String> autocompleteEntries = autocomplete.getAutocompleteEntries(commandText);
        if (autocompleteEntries.isEmpty()) {
            autocompletePopup.getItems().clear();
            autocompletePopup.hide();
        } else {
            // force the originalSearchInput to be the first item
            autocompleteEntries.remove(commandText);
            autocompleteEntries.add(0, commandText);

            populatePopup(autocompleteEntries, commandText);

            if (!autocompletePopup.isShowing()) {
                autocompletePopup.show(commandTextField, Side.BOTTOM, 0, 0);
            }
        }
    }

    /**
     * Generates a list of autocomplete entries in the {@code contextMenu}.
     * Solution below adapted from https://stackoverflow.com/questions/36861056/javafx-textfield-auto-suggestions.
     *
     * @param autocompleteEntries The list of matching strings.
     * @param originalSearchInput The original search input entered by the user.
     */
    private void populatePopup(List<String> autocompleteEntries, String originalSearchInput) {
        assert !autocompleteEntries.isEmpty();

        List<CustomMenuItem> menuItems = new LinkedList<>();
        for (String autocompleteEntry : autocompleteEntries) {
            Label entryLabel = new Label();
            entryLabel.setGraphic(buildTextFlow(originalSearchInput, autocompleteEntry));
            entryLabel.setPrefSize(getLabelPreferredWidth(), LABEL_PREFERRED_HEIGHT);
            labelWidthChangeListener(entryLabel);

            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            // Whenever an item is selected, set text field to the selected text, execute the command and close pop up.
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    commandTextField.setText(autocompleteEntry);
                    handleCommandEntered();
                    autocompletePopup.hide();
                    autocompletePopup.getItems().clear();
                }
            });

            menuItems.add(item);
        }

        // Update the autocomplete pop up.
        autocompletePopup.getItems().clear();
        autocompletePopup.getItems().addAll(menuItems);
    }

    /**
     * Builds the {@code TextFlow} to style the given {@code originalSearchInput} in the {@code autocompleteEntry}.
     * The {@code autocompleteEntry} must start with {@code originalSearchInput}.
     * Solution below adapted from https://stackoverflow.com/questions/36861056/javafx-textfield-auto-suggestions.
     *
     * @param originalSearchInput The original search input entered by the user.
     * @param autocompleteEntry The autocomplete entry generated by the original search input.
     * @return A {@code TextFlow} with the originalSearchInput being styled.
     */
    private TextFlow buildTextFlow(String originalSearchInput, String autocompleteEntry) {
        Text textBefore = new Text(autocompleteEntry.substring(0, originalSearchInput.length()));
        Text textAfter = new Text(autocompleteEntry.substring(originalSearchInput.length()));
        textBefore.setFill(Color.ORANGE);
        return new TextFlow(textBefore, textAfter);
    }

    /**
     * Activates a listener to detect the change in the application size and changes the label width accordingly.
     *
     * @param label The label which the width will be changed.
     */
    private void labelWidthChangeListener(Label label) {
        commandTextField.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label.setPrefWidth(getLabelPreferredWidth());
            }
        });
    }

    /**
     * Gets the label's preferred width.
     *
     * @return Label's preferred width.
     */
    private double getLabelPreferredWidth() {
        // The length of the width to be minus so the label's width matches the white line on commandTextField
        double widthFromEndOfCommandTextField = 13;
        return commandTextField.getWidth() - widthFromEndOfCommandTextField;
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
