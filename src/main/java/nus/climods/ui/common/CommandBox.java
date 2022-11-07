package nus.climods.ui.common;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import nus.climods.commons.core.CommandSession;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.storage.exceptions.StorageException;
import nus.climods.ui.UiPart;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandSession commandSession;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandSession commandSession) {
        super(FXML);
        this.commandSession = commandSession;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.TAB)) {
                commandTextField.requestFocus();
            }

            if (!commandTextField.isFocused()) {
                return;
            }

            switch (event.getCode()) {
            case UP:
                commandTextField.setText(commandSession.getPreviousCommand());
                break;
            case DOWN:
                commandTextField.setText(commandSession.getNextCommand());
                break;
            default:
                break;
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
            commandSession.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException | StorageException e) {
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
}
